package com.example.ytest.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class QueryResult(
    @SerializedName("msg")
    val msg: String,

    @SerializedName("data")
    val data: List<Product>
)