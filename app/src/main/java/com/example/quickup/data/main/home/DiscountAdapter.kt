package com.example.quickup.data.main.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quickup.data.model.ProductsModel
import com.example.quickup.databinding.DiscountItemBinding

import com.squareup.picasso.Picasso

class DiscountAdapter : RecyclerView.Adapter<DiscountAdapter.ViewHolder>() {
    private val discountList = ArrayList<ProductsModel>()
    var onFavoritesClick :(ProductsModel)-> Unit = {}
    var onBasketClick :(ProductsModel)-> Unit ={}
   inner class ViewHolder (var discountItemBinding: DiscountItemBinding) : RecyclerView.ViewHolder(discountItemBinding.root) {
        fun bind(item : ProductsModel ){
            discountItemBinding.apply {
                productsModel = item
                item.image.let {
                    Picasso.get().load(it).into(discountimageview)
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
        val discountItemBinding = DiscountItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(discountItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(discountList[position])
    }

    override fun getItemCount(): Int {
        return discountList.size
    }

    fun updateProductsList(newdiscountedList: List<ProductsModel>) {
        discountList.clear()
        discountList.addAll(newdiscountedList)
        notifyDataSetChanged()
    }
}
