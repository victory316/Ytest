package com.example.ytest.data

import androidx.paging.PagedList
import com.example.ytest.data.local.Product
import com.example.ytest.viewmodel.MainViewModel
import timber.log.Timber

class DataBoundaryCallback(
    private val viewModel: MainViewModel
) : PagedList.BoundaryCallback<Product>() {

    override fun onZeroItemsLoaded() {
    }

    override fun onItemAtEndLoaded(itemAtEnd: Product) {
//        viewModel.getPagedList()
    }
}