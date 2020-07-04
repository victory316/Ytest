package com.example.ytest.util

import androidx.room.TypeConverter
import com.example.ytest.data.local.Description
import com.google.gson.Gson

class MyTypeConverter {

    @TypeConverter
    fun appToString(description: Description): String = Gson().toJson(description)

    @TypeConverter
    fun stringToApp(string: String): Description = Gson().fromJson(string, Description::class.java)
}