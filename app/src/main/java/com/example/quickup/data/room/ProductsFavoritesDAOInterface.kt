package com.example.quickup.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quickup.data.model.ProductsModel

@Dao
interface ProductsFavoritesDAOInterface {

    @Insert
    fun addProductsFavorites(productsModel: ProductsModel)

    @Query("SELECT * FROM productsfavoritesdatabase")
    fun getProductsFavorites(): List<ProductsModel>?

    @Query("SELECT title FROM productsfavoritesdatabase")
    fun getProductstitleFavorites(): List<String>?

    @Query("DELETE FROM productsfavoritesdatabase WHERE id = :idInput")
    fun deleteProductsWithId(idInput: Int)

    @Query("DELETE FROM productsfavoritesdatabase")
    fun clearFavorites()

}