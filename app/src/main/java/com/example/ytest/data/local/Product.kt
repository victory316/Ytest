package com.example.ytest.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product")
data class Product(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "thumbnail")
    @SerializedName("thumbnail")
    val thumbnail: String,
//
//    @ColumnInfo(name = "description")
//    @SerializedName("description")
//    val description: Description,

    @ColumnInfo(name = "productId")
    val productId: Int,

    @ColumnInfo(name = "imagePath")
    @SerializedName("imagePath")
    val imagePath: String,

    @ColumnInfo(name = "subject")
    @SerializedName("subject")
    val subject: String,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price: Int

//    @ColumnInfo(name = "rate")
//    @SerializedName("rate")
//    val rate: Double
)