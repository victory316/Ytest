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

    @Query("UPDATE product SET favorite_status = :isExists")
    fun updateFavoriteStatus(isExists: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavorite(favorite: Product)

    @Query("DELETE FROM favorite WHERE id == :id")
    fun deleteFavorite(id: Int)

    @Query("DELETE FROM product")
    fun deleteAllList()

    @Query("SELECT * FROM product WHERE id == :id")
    fun getProductWithId(id: Int): LiveData<Product>
}