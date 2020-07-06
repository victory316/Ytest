package com.example.ytest.viewmodel

import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.ytest.data.DataBoundaryCallback
import com.example.ytest.data.MainRepository
import com.example.ytest.data.local.Favorite
import com.example.ytest.data.local.Product
import timber.log.Timber

class MainViewModel internal constructor(
    private val repository: MainRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // 아이템을 고를 경우 해당 아이템의 id를 전달하는 LiveData
    private val _detailViewId = MutableLiveData<Int>()
    val detailViewId: LiveData<Int>
        get() = _detailViewId

    private var factory: DataSource.Factory<Int, Product> =
        repository.getAllPaged()

    private var pagedList: LiveData<PagedList<Product>>

    init {

        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPageSize(20)
            .setPrefetchDistance(5)
            .build()

        val boundaryCallback =
            DataBoundaryCallback(this)

        val pagedListBuilder: LivePagedListBuilder<Int, Product> =
            LivePagedListBuilder(
                factory,
                config
            ).setBoundaryCallback(boundaryCallback)

        pagedList = pagedListBuilder.build()
    }

    val favoriteList: LiveData<List<Favorite>> = getSavedFavorite().switchMap {
        repository.getFavoriteList()
    }

    fun doPaging() {
        repository.doPaging()
    }

    fun getPagedList() = pagedList

    private fun getSavedFavorite(): MutableLiveData<Int> {
        return savedStateHandle.getLiveData(FAVORITE_SAVED_STATE_KEY, NO_FAVORITE)
    }

    fun showDetailView(id: Int) {
        _detailViewId.postValue(id)
    }

    fun toggleFavorite(product: Product) {
        repository.saveFavorite(product)
    }

    fun deleteFavorite(id: Int) {
        repository.deleteFavorite(id)
    }

    fun cleanData() {
//        repository.cleanData()
    }

    companion object {
        private const val NO_FAVORITE = -1
        private const val FAVORITE_SAVED_STATE_KEY = "FAVORITE_SAVED_STATE_KEY"
    }
}