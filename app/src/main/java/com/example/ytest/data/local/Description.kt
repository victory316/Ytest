package com.example.ytest.data.local

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

/**
 *  숙소의 상세정보를 보여주기 위한 data class
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