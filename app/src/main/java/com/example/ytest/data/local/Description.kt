package com.example.ytest.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//@Entity(tableName = "description")
data class Description(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "imagePath")
    @SerializedName("imagePath")
    val imagePath: String,

    @ColumnInfo(name = "subject")
    @SerializedName("subject")
    val subject: String,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price: Int
)