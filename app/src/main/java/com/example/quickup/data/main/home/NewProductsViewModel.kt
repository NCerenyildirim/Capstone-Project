package com.example.quickup.data.main.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quickup.data.model.CRUDModel
import com.example.quickup.data.model.ProductsModel
import com.example.quickup.data.repository.ProductsRepository

class NewProductsViewModel(context: Context) : ViewModel() {
     private val modelRepo = ProductsRepository(context)
     private var _productsList = MutableLiveData<List<ProductsModel>>()
    val productsList: LiveData<List<ProductsModel>>
        get() = _productsList

    private var _crudModel = MutableLiveData<CRUDModel>()
    val crudModel: LiveData<CRUDModel>
        get() = _crudModel


    init {
        getProducts()
    }
    private fun getProducts() {
        modelRepo.products()
        _productsList = modelRepo.productsList

    }
    fun addToBag(user:String,title: String,price: Double?, description: String,category: String,image: String?,rate: Double,count: Int,saleState: Int,){
        modelRepo.addToBag(user, title, price, description, category, image, rate, count, saleState)
        _crudModel = modelRepo.crudResponse
    }
    fun addToFavorites(productsModel: ProductsModel){
        modelRepo.addProductsToFavorites(productsModel)
        _crudModel = modelRepo.crudResponse
    }

}