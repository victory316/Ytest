package com.example.ytest.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.ytest.data.local.Favorite
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

    @Query("SELECT * FROM favorite")
    fun getFavoriteList(): LiveData<List<Favorite>>

    @Query("UPDATE product SET favorite_status = :isExists WHERE id == :id")
    fun updateFavoriteStatus(id: Int, isExists: Boolean)

    @Query("SELECT count(*) FROM favorite WHERE id == :id")
    fun checkFavoriteExists(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite WHERE id == :id")
    fun deleteFavorite(id: Int)

    @Query("DELETE FROM product")
    fun deleteAllList()

    @Query("SELECT * FROM product WHERE id == :id")
    fun getProductWithId(id: Int): LiveData<Product>

    @Query("SELECT * FROM product")
    fun getAllPaged(): DataSource.Factory<Int, Product>
}