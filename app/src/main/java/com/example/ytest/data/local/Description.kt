package com.example.ytest.data.local

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

/**
 *  Description에 대한 정보를 담는 data class
 */

data class Description(
    @ColumnInfo(name = "imagePath")
    @SerializedName("imagePath")
    val imagePath: String?,

    @ColumnInfo(name = "subject")
    @SerializedName("subject")
    val subject: String?,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price: Int?
)