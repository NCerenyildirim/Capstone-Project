package com.example.quickup.data.main.productsbasket

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quickup.data.model.CRUDModel
import com.example.quickup.data.model.ProductsModel
import com.example.quickup.data.repository.ProductsRepository

class ProductsBasketViewModel(context: Context) : ViewModel() {

    private val productsRepo = ProductsRepository(context)

    private var _productsBasket = MutableLiveData<List<ProductsModel>>()
    val productsbasket: LiveData<List<ProductsModel>>
        get() = _productsBasket

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var _crudModel = MutableLiveData<CRUDModel>()
    val crudModel: LiveData<CRUDModel>
        get() = _crudModel


     fun getproductsBasket(user:String) {
        productsRepo.getBagProducts(user)
        _productsBasket = productsRepo.productsbasketList
        _isLoading = productsRepo.isLoading
    }

    fun deleteFromBag(id: Int,user:String) {
        productsRepo.deleteFromBag(id)
        getproductsBasket(user)
    }
}
