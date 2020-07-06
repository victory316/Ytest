package com.example.ytest.data

import androidx.lifecycle.LiveData
import com.example.ytest.data.local.Favorite
import com.example.ytest.data.local.Product
import com.example.ytest.data.remote.BasicClient
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class DetailRepository private constructor(private val dao: AnswerDao) {
    private var saveDisposable: Disposable? = null
    private var deleteDisposable: Disposable? = null

    fun requestProductWithId(id: Int): LiveData<Product> {
        return dao.getProductWithId(id)
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
    companion object {

        @Volatile
        private var instance: DetailRepository? = null

        fun getInstance(dao: AnswerDao) =
            instance ?: synchronized(this) {
                instance ?: DetailRepository(dao).also { instance = it }
            }
    }
}
