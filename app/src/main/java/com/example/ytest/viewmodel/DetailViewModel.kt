package com.example.ytest.viewmodel

import androidx.lifecycle.*
import com.example.ytest.data.AnswerRepository
import com.example.ytest.data.DetailRepository
import com.example.ytest.data.local.Product

class DetailViewModel internal constructor(
    private val repository: DetailRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val dataList = repository.getProductList()

    private var requestId = 0

    val queryList: LiveData<List<Product>> = getSavedFavorite().switchMap {
        repository.getProductList()
    }

    val requestedData: LiveData<Product> = getSavedFavorite().switchMap {
        repository.requestProductWithId(requestId)
    }

    fun setRequestId(id: Int) {
        requestId = id
    }

    fun deleteFavorite(id: Int) {
        repository
    }

    private fun getSavedFavorite(): MutableLiveData<Int> {
        return savedStateHandle.getLiveData(FAVORITE_SAVED_STATE_KEY, NO_FAVORITE)
    }

    fun requestProduct(id: Int) {
        getSavedFavorite().switchMap {
            repository.requestProductWithId(id)
        }
    }

    companion object {
        private const val NO_FAVORITE = -1
        private const val FAVORITE_SAVED_STATE_KEY = "FAVORITE_SAVED_STATE_KEY"
    }
}