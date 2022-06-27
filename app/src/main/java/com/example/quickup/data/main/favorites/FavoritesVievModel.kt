package com.example.quickup.data.main.favorites

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quickup.data.model.CRUDModel
import com.example.quickup.data.model.ProductsModel
import com.example.quickup.data.repository.ProductsRepository

class FavoritesViewModel (context: Context) : ViewModel() {

    private val productsRepo = ProductsRepository(context)

    private var _favoritesBasket = MutableLiveData<List<ProductsModel>>()
    val productsfavorites: LiveData<List<ProductsModel>>
        get() = _favoritesBasket

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _crudModel = MutableLiveData<CRUDModel>()
    val crudModel: LiveData<CRUDModel>
        get() = _crudModel

    init {
        getproductsFavorites()
    }

    private fun getproductsFavorites() {
        productsRepo.productsFavorites()
        _favoritesBasket = productsRepo.favoritesBasketList
        _isLoading = productsRepo.isLoading
    }

    fun deleteFavoritesFromBasket(Id: Int) {
        productsRepo.deleteFavoritesFromBasket(Id)
        getproductsFavorites()
    }
}
