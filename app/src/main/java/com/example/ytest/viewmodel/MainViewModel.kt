package com.example.ytest.viewmodel

import androidx.lifecycle.*
import com.example.ytest.data.AnswerRepository
import com.example.ytest.data.local.Product

class MainViewModel internal constructor(
    private val repository: AnswerRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val dataList = repository.getProductList()

    val queryList: LiveData<List<Product>> = getSavedFavorite().switchMap {
        repository.getProductList()
    }

    private fun getSavedFavorite(): MutableLiveData<Int> {
        return savedStateHandle.getLiveData(FAVORITE_SAVED_STATE_KEY, NO_FAVORITE)
    }

    companion object {
        private const val NO_FAVORITE = -1
        private const val FAVORITE_SAVED_STATE_KEY = "FAVORITE_SAVED_STATE_KEY"
    }
}