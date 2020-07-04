package com.example.ytest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ytest.data.local.Product


//@Database(entities = [BasicData::class, ProblemData::class], version = 1)
@Database(entities = [Product::class], version = 1)
abstract class MainDatabase : RoomDatabase() {

    //    abstract fun githubDao(): BasicDao
    abstract fun answerDao(): AnswerDao

    companion object {
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase? {
            if (INSTANCE == null) {
                synchronized(MainDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MainDatabase::class.java, "github_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}