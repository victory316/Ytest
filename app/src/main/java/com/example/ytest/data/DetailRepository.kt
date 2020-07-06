package com.example.ytest.data

import androidx.lifecycle.LiveData
import com.example.ytest.data.local.Favorite
import com.example.ytest.data.local.Product
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 *  디테일 페이지에서의 상호작용에 사용되는 DetailRepository
 */
class DetailRepository private constructor(private val dao: MainDao) {
    private var saveDisposable: Disposable? = null
    private var deleteDisposable: Disposable? = null

    // Intent로 전달받은 id를 가지고 Room에 Product를 요청
    fun requestProductWithId(id: Int): LiveData<Product> {
        return dao.getProductWithId(id)
    }

    // 즐겨찾기 버튼을 누를 경우 해당 product와 누른 시점의 Timestamp를 담아 Favorite class로 저장
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

    // 즐겨찾기 삭제
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

    companion object {

        @Volatile
        private var instance: DetailRepository? = null

        fun getInstance(dao: MainDao) =
            instance ?: synchronized(this) {
                instance ?: DetailRepository(dao).also { instance = it }
            }
    }
}
