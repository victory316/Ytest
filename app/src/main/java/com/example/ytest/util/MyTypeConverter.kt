package com.example.ytest.util

import androidx.room.TypeConverter
import com.example.ytest.data.local.Description
import com.google.gson.Gson

/**
 *  Product class를 Room에 저장 및 로드를 위한 TypeConverter
 */
class MyTypeConverter {

    @TypeConverter
    fun appToString(description: Description): String = Gson().toJson(description)

    @TypeConverter
    fun stringToApp(string: String): Description = Gson().fromJson(string, Description::class.java)
}