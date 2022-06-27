package com.example.quickup.data.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quickup.data.model.ProductsModel
import com.example.quickup.databinding.ProductsItemBinding

import com.squareup.picasso.Picasso

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private val productsList = ArrayList<ProductsModel>()
    var onFavoritesClick :(ProductsModel)-> Unit = {}
    var onBasketClick :(ProductsModel)-> Unit ={}
  inner  class ViewHolder (var productsItemBinding: ProductsItemBinding) : RecyclerView.ViewHolder(productsItemBinding.root) {
        fun bind(item : ProductsModel ){
            productsItemBinding.apply {
                productsModel = item
                item.image.let {
                    Picasso.get().load(it).into(productsimageview)
                }
                addFavoritesImage.setOnClickListener {
                    onFavoritesClick(item)

                }
                addBasketImage.setOnClickListener {
                    onBasketClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val productsItemBinding =
            ProductsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(productsItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productsList[position])
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    fun updateProductsList(newdiscountedList : List<ProductsModel>){
        productsList.clear()
        productsList.addAll(newdiscountedList)
        notifyDataSetChanged()
    }
}