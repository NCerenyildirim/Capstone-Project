package com.example.quickup.data.service


import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProductsAPIService {

    private val BASE_URL = "https://canerture.com"
 companion object{
     fun getAPI(baseUrl: String):Retrofit {
         return Retrofit.Builder()
             .baseUrl(baseUrl)
             .addConverterFactory(GsonConverterFactory.create())
             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
             .build()


     }
 }


}