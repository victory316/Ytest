package com.example.ytest.viewmodel

import androidx.lifecycle.*
import com.example.ytest.data.DetailRepository
import com.example.ytest.data.local.Product
import timber.log.Timber

class DetailViewModel internal constructor(
    private val repository: DetailRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var requestId = 0

    val requestedData: LiveData<Product> = getSavedFavorite().switchMap {
        repository.requestProductWithId(requestId)
    }

    fun setRequestId(id: Int) {
        requestId = id
    }

    fun deleteFavorite() {
        requestedData.value?.let {
            Timber.tag("toggleTest").d("deleting! : ${it.id}")
            repository.deleteFavorite(it.id)
        }

    }

    fun toggleFavorite() {
        requestedData.value?.let {
            repository.saveFavorite(it)
            Timber.tag("toggleTest").d("toggle! : $it")
        }
    }

    private fun getSavedFavorite(): MutableLiveData<Int> {
        return savedStateHandle.getLiveData(FAVORITE_SAVED_STATE_KEY, NO_FAVORITE)
    }

    companion object {
        private const val NO_FAVORITE = -1
        private const val FAVORITE_SAVED_STATE_KEY = "FAVORITE_SAVED_STATE_KEY"
    }
}