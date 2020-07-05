package com.example.ytest.data

import androidx.lifecycle.LiveData
import com.example.ytest.data.local.Product
import com.example.ytest.data.remote.BasicClient
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class DetailRepository private constructor(private val dao: AnswerDao) {
    private var disposable: Disposable? = null
    private var transactionDisposable: Disposable? = null
    private var pageCount = 1

    fun requestProductWithId(id: Int): LiveData<Product> {
        Timber.tag("Test").d("requested data : ${dao.getProductWithId(id)}")

        return dao.getProductWithId(id)
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
                        }


                    }, {
                        it.printStackTrace()
                    })


            return dao.getProductList()
        }
        return dao.getProductList()
    }

    fun deleteFavorite(id: Int) {
        dao.deleteFavorite(id)
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
        private var instance: DetailRepository? = null

        fun getInstance(dao: AnswerDao) =
            instance ?: synchronized(this) {
                instance ?: DetailRepository(dao).also { instance = it }
            }
    }
}
