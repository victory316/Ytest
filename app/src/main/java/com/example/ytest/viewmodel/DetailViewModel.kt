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

    // 아이템을 고를 경우 해당 아이템의 id를 전달하는 LiveData
    private val _detailViewId = MutableLiveData<Int>()
    val detailViewId: LiveData<Int>
        get() = _detailViewId

    init {
        repository.cleanData()
    }

    val queryList: LiveData<List<Product>> = getSavedFavorite().switchMap {
        repository.getProductList()
    }

    fun testQuery() {
        repository.requestQuery()
    }

    private fun getSavedFavorite(): MutableLiveData<Int> {
        return savedStateHandle.getLiveData(FAVORITE_SAVED_STATE_KEY, NO_FAVORITE)
    }

    fun showDetailView(id: Int) {
        _detailViewId.postValue(id)
    }

    companion object {
        private const val NO_FAVORITE = -1
        private const val FAVORITE_SAVED_STATE_KEY = "FAVORITE_SAVED_STATE_KEY"
    }
}