package com.example.ytest.data

import androidx.lifecycle.LiveData
import com.example.ytest.data.local.Product
import com.example.ytest.data.remote.BasicClient
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class AnswerRepository private constructor(private val dao: AnswerDao) {
    private var disposable : Disposable? = null

    fun getProductList(): LiveData<List<Product>> {

        if (dao.getProductList().value.isNullOrEmpty()) {
            disposable = BasicClient().getApi()
                .loadPlace(1)
                .observeOn(Schedulers.trampoline())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { result ->

                        Timber.tag("test").d("result : $result")

                        for (data in result.data) {
                            Timber.tag("test").d("$data")
                        }

//                        repository.addData(result.problems)

                    }, {
                        it.printStackTrace()
                    })


        }
        return dao.getProductList()
    }

    fun addProductList(list: List<Product>) {
        dao.addProductResult(list)
    }

    companion object {

        @Volatile
        private var instance: AnswerRepository? = null

        fun getInstance(dao: AnswerDao) =
            instance ?: synchronized(this) {
                instance ?: AnswerRepository(dao).also { instance = it }
            }
    }
}
