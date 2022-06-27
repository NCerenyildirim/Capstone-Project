package com.example.quickup.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.quickup.data.model.CRUDModel
import com.example.quickup.data.model.ProductsModel
import com.example.quickup.data.room.ProductsFavoritesDAOInterface
import com.example.quickup.data.room.ProductsFavoritesRoomDatabase
import com.example.quickup.utils.APIUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ProductsRepository (context : Context) {
    var productsList = MutableLiveData<List<ProductsModel>>()

    var discountList = MutableLiveData<List<ProductsModel>>()

   var productsbasketList = MutableLiveData<List<ProductsModel>>()

    var favoritesBasketList = MutableLiveData<List<ProductsModel>>()

    var crudResponse = MutableLiveData<CRUDModel>()
    var isLoading = MutableLiveData<Boolean>()

    var isFavoritesAddedBasket = MutableLiveData<Boolean>()

    private val productsDAOAPI = APIUtils.getProductsDAO()

    private val productsFavoritesDAOInterface: ProductsFavoritesDAOInterface? =
        ProductsFavoritesRoomDatabase.productsFavoritesRoomDatabase(context)?.productsFavoritesDAOInterface()


    val disposable = CompositeDisposable()

    fun discount() {
        disposable.add(
            productsDAOAPI.getDiscount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //succes
                    discountList.value = it
                }, {
                    //error
                    Log.w("Discount", "ERROR", it)
                })
        )
    }

    fun products() {
        disposable.add(
            productsDAOAPI.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //succes
                    productsList.value = it
                }, {
                    //error
                    Log.w("Products", "ERROR", it)
                })
        )
    }
    fun getBagProducts(user:String) {
        disposable.add(
            productsDAOAPI.getBagProducts(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //succes
                    productsbasketList.value = it
                }, {
                    //error
                    Log.w("Products", "ERROR", it)
                })
        )
    }
        fun addToBag(user:String,title: String,price: Double?, description: String,category: String,image: String?,rate: Double,count: Int,saleState: Int,){
            disposable.add(
                productsDAOAPI.addToBag(user, title, price, description, category, image, rate, count, saleState)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        //succes
                        crudResponse.value = it
                    }, {
                        //error
                        Log.w("Add To Bag", "ERROR", it)
                    })
            )

        }

    fun deleteFromBag(id:Int){
        disposable.add(
            productsDAOAPI.deleteFromBag(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //succes
                    crudResponse.value = it
                }, {
                    //error
                    Log.w("Delete To Bag", "ERROR", it)
                })
        )

    }

    fun productsFavorites() {
        isLoading.value = true

        productsFavoritesDAOInterface?.getProductsFavorites()?.let {
            favoritesBasketList.value = it
            isLoading.value = false
        } ?: run {
            isLoading.value = false
        }
    }

    fun addProductsToFavorites(productsModel: ProductsModel) {

        productsFavoritesDAOInterface?.getProductstitleFavorites()?.let {

            isFavoritesAddedBasket.value = if (it.contains(productsModel.title).not()) {
                productsFavoritesDAOInterface.addProductsFavorites(productsModel)
                true
            } else {
                false
            }
        }
    }

    fun deleteFavoritesFromBasket(ıd: Int) {
        productsFavoritesDAOInterface?.deleteProductsWithId(ıd)
    }

    fun clearFavorites() {
        productsFavoritesDAOInterface?.clearFavorites()
    }

}
