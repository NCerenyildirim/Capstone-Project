package com.example.quickup.data.service

import com.example.quickup.data.model.CRUDModel
import com.example.quickup.data.model.ProductsModel
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsDAOAPI {

    @GET("api/ecommerce/get_products.php")
    fun getProducts(): Observable<List<ProductsModel>>

    @GET("api/ecommerce/get_sale_products.php")
    fun getDiscount() : Observable<List<ProductsModel>>

    @POST("api/ecommerce/get_bag_products_by_user.php")
    @FormUrlEncoded
    fun getBagProducts( @Field("user") user: String,) : Observable<List<ProductsModel>>

    @POST("api/ecommerce/delete_from_bag.php")
    @FormUrlEncoded
    fun deleteFromBag( @Field("id") id:Int,) : Observable<CRUDModel>

    @POST("api/ecommerce/add_to_bag.php")
    @FormUrlEncoded
    fun addToBag(
        @Field("user") user: String,
        @Field("title") title: String,
        @Field("price") price: Double?,
        @Field("description") description: String,
        @Field("category") category: String,
        @Field("image") image: String?,
        @Field("rate") rate: Double,
        @Field("count") count: Int,
        @Field("sale_state") saleState: Int,

    ): Observable<CRUDModel>

}