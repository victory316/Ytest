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
        Timber.tag("paging").d("end loaded!")

        viewModel.getPagedList()

        // 뷰모델 및 레포지토리 재정립을 끝낸 이후 페이징 재구현할것.
//        viewModel.doSearchByPaging()
    }
}