package com.example.quickup.utils

import com.example.quickup.data.service.ProductsAPIService
import com.example.quickup.data.service.ProductsDAOAPI

class APIUtils {

    companion object{
        val BASE_URL = "https://canerture.com"
        fun getProductsDAO(): ProductsDAOAPI{
            return ProductsAPIService.getAPI(BASE_URL).create(ProductsDAOAPI::class.java)

        }
    }
}