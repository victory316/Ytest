package com.example.ytest.data.local

import com.google.gson.annotations.SerializedName

/**
 *  REST API로부터 받은 가장 겉단의 data class
 */
data class QueryResult(
    @SerializedName("msg")
    val msg: String,

    @SerializedName("data")
    val data: ProductResult,

    @SerializedName("code")
    val code: Int
)