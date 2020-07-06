package com.example.ytest.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.ytest.data.local.Favorite
import com.example.ytest.data.local.Product
import com.example.ytest.data.remote.BasicClient
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/**
 *  메인 화면에서의 상호작용에 사용되는 repository
 */
class MainRepository private constructor(private val dao: MainDao) {
    private var disposable: Disposable? = null
    private var transactionDisposable: Disposable? = null
    private var saveDisposable: Disposable? = null
    private var deleteDisposable: Disposable? = null
    private var pageCount = 1

    private var pagingErrorSubject = PublishSubject.create<Boolean>()
    private var transactionErrorSubject = PublishSubject.create<Boolean>()

    fun getPagingErrorSubject(): PublishSubject<Boolean> {
        return pagingErrorSubject
    }

    fun getTransactionErrorSubject(): PublishSubject<Boolean> {
        return transactionErrorSubject
    }

    fun getAllPaged(): DataSource.Factory<Int, Product> {
        disposable = BasicClient()
            .getApi().loadPlace(pageCount)
            .observeOn(Schedulers.computation())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                dao.addProductResult(result.data.product)

                result.data.product.forEach { product ->
                    dao.updateFavoriteStatus(
                        product.id,
                        dao.checkFavoriteExists(product.id) == 1
                    )
                }

            }, { error ->

                pagingErrorSubject.onNext(true)
                error.printStackTrace()
            })

        return dao.getAllPaged()
    }

    fun doPaging() {
        if (pageCount != 4) {
            disposable = BasicClient()
                .getApi().loadPlace(pageCount)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    dao.addProductResult(result.data.product)

                    result.data.product.forEach { product ->
                        dao.updateFavoriteStatus(
                            product.id,
                            dao.checkFavoriteExists(product.id) == 1
                        )
                    }

                    if (pageCount != 4) {
                        pageCount++
                    }

                }, { error ->

                    pagingErrorSubject.onNext(true)
                    error.printStackTrace()
                })
        }
    }

    fun getFavoriteList(): LiveData<List<Favorite>> {
        return dao.getFavoriteList()
    }

    // 즐겨찾기 버튼 선택시 해당 Product를 timestamp와 함께 Room에 저장하고 즐겨찾기 유무를 기존 아이템들에 반영
    fun saveFavorite(product: Product) {
        Favorite(
            product.id, product.name, product.thumbnail, product.description, product.rate,
            System.currentTimeMillis()
        ).let { favorite ->

            saveDisposable = Observable
                .just(true)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    dao.updateFavoriteStatus(favorite.id, true)
                    dao.saveFavorite(favorite)
                    saveDisposable?.dispose()
                }, {
                    transactionErrorSubject.onNext(true)
                    it.printStackTrace()
                })
        }
    }

    // 즐겨찾기 데이터의 삭제
    fun deleteFavorite(id: Int) {
        deleteDisposable = Observable
            .just(true)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {

                    dao.deleteFavorite(id)
                    dao.updateFavoriteStatus(id, false)
                    deleteDisposable?.dispose()
                }, {
                    transactionErrorSubject.onNext(true)
                    it.printStackTrace()
                })
    }


    // onDestroy 호출시 즐겨찾기 리스트를 제외한 데이터를 clear
    fun cleanData() {
        transactionDisposable = Observable
            .just(true)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                dao.deleteAllList()

                transactionDisposable?.dispose()
                pageCount = 1
            }, {
                transactionErrorSubject.onNext(true)
                it.printStackTrace()
            })
    }

    companion object {

        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(dao: MainDao) =
            instance ?: synchronized(this) {
                instance ?: MainRepository(dao).also { instance = it }
            }
    }
}
