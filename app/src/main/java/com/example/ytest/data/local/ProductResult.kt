package com.example.ytest.data.local

import com.google.gson.annotations.SerializedName

/**
 *  total count와 Product를 담는 data class
 */
data class ProductResult(
    @SerializedName("totalCount")
    val totalCount: Int,

    @SerializedName("product")
    val product: List<Product>
)