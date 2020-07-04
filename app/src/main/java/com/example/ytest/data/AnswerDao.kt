package com.example.ytest.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ytest.data.local.Product

@Dao
interface AnswerDao {

    @Transaction
    open fun addProductResult(list: List<Product>) {
        addProductList(list)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProductList(list: List<Product>)

    @Query("SELECT * FROM product")
    fun getProductList() : LiveData<List<Product>>

    @Query("DELETE FROM product")
    fun deleteAllList()
}