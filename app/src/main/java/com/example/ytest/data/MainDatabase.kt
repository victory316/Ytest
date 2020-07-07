package com.example.ytest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ytest.data.local.Favorite
import com.example.ytest.data.local.Product
import com.example.ytest.util.MyTypeConverter

/**
 *  Room database
 */
@TypeConverters(MyTypeConverter::class)
@Database(entities = [Product::class, Favorite::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract fun answerDao(): MainDao

    companion object {
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase? {
            if (INSTANCE == null) {
                synchronized(MainDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MainDatabase::class.java, "main_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}