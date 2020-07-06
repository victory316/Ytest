package com.example.ytest.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 *
 *  즐겨찾기로 등록한 숙소를 저장하기 위한 data class
 *
 *  - Product data와 유사하나 저장 시점을 담는 savedTime을 받는 것이 차
 */
@Entity(tableName = "favorite")
data class Favorite(

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

    @ColumnInfo(name = "savedTime")
    val savedTime: Long
)