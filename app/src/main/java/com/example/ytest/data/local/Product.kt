package com.example.ytest.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 *  숙소 정보와 썸네일 등을 저장하는 data class
 */
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
    val thumbnail: String?,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: Description,

    @ColumnInfo(name = "rate")
    @SerializedName("rate")
    val rate: Double,

    @ColumnInfo(name = "favorite_status")
    val favoriteStatus: Boolean
)