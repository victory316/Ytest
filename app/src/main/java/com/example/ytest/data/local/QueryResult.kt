package com.example.ytest.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * REST 통신을 통해 최초로 들어오는 데이터를 담기 위한 data class
 */
data class QueryResult(
    @SerializedName("msg")
    val msg: String,

    @SerializedName("data")
    val data: ProductResult,

    @SerializedName("code")
    val code: Int
)