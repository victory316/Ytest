package com.example.ytest.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.ytest.data.local.Favorite
import com.example.ytest.data.local.Product
import com.example.ytest.data.remote.BasicClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class MainRepository private constructor(private val dao: AnswerDao) {
    private var disposable: Disposable? = null
    private var transactionDisposable: Disposable? = null
    private var saveDisposable: Disposable? = null
    private var deleteDisposable: Disposable? = null
    private var pageCount = 1


    fun getAllPaged(): DataSource.Factory<Int, Product> {

//        if (pageCount != 3) {
//            pageCount++
//        }
//
//        Timber.d("pageCount : $pageCount")

        dao.getProductList().value?.let { list ->
            if (list.isNotEmpty()) {
                return dao.getAllPaged()
            } else {

                // Gihub search query로 찾고자 하는 유저를 검색
                disposable = BasicClient()
                    .getApi().loadPlace(pageCount)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        dao.addProductResult(result.data.product)

                        for (data in result.data.product) {
                            Timber.tag("test").d("$data")
                            Timber.tag("test").d("exists? : ${dao.checkFavoriteExists(data.id)}")

                            dao.updateFavoriteStatus(
                                data.id,
                                dao.checkFavoriteExists(data.id) == 1
                            )
                        }

                    }, { error ->
                        run {
                            error.printStackTrace()
                        }
                    })
            }
        }

        return dao.getAllPaged()
    }

    fun getProductList(): LiveData<List<Product>> {

        if (dao.getProductList().value.isNullOrEmpty()) {
            disposable = BasicClient().getApi()
                .loadPlace(pageCount)
                .observeOn(Schedulers.trampoline())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { result ->

                        dao.addProductResult(result.data.product)

                        Timber.tag("queryTest").d("result : $result")

                        for (data in result.data.product) {
                            Timber.tag("test").d("$data")
                            Timber.tag("test").d("exists? : ${dao.checkFavoriteExists(data.id)}")

                            dao.updateFavoriteStatus(
                                data.id,
                                dao.checkFavoriteExists(data.id) == 1
                            )
                        }


                    }, {
                        it.printStackTrace()
                    })


            return dao.getProductList()
        }
        return dao.getProductList()
    }

    fun getFavoriteList(): LiveData<List<Favorite>> {
        return dao.getFavoriteList()
    }

    fun saveFavorite(product: Product) {
        Favorite(
            product.id, product.name, product.thumbnail, product.description, product.rate,
            System.currentTimeMillis()
        ).let { favorite ->

            saveDisposable = Observable
                .just(true)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    dao.updateFavoriteStatus(favorite.id, true)
                    dao.saveFavorite(favorite)

                    saveDisposable?.dispose()
                }
        }
    }

    fun deleteFavorite(id: Int) {
        deleteDisposable = Observable
            .just(true)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe {
                dao.deleteFavorite(id)
                dao.updateFavoriteStatus(id, false)

                deleteDisposable?.dispose()
            }
    }

    fun cleanData() {
        transactionDisposable = Observable
            .just(true)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe {
                dao.deleteAllList()

                transactionDisposable?.dispose()
            }
    }

    companion object {

        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(dao: AnswerDao) =
            instance ?: synchronized(this) {
                instance ?: MainRepository(dao).also { instance = it }
            }
    }
}
