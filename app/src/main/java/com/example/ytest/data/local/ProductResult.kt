package com.example.ytest.data.local

import com.google.gson.annotations.SerializedName

data class ProductResult(
    @SerializedName("totalCount")
    val totalCount: Int,

    @SerializedName("product")
    val product: List<Product>
)