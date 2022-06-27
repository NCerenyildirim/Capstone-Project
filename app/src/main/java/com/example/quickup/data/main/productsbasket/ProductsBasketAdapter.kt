package com.example.quickup.data.main.productsbasket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quickup.data.model.ProductsModel
import com.example.quickup.databinding.BasketItemBinding
import com.squareup.picasso.Picasso

class ProductsBasketAdapter : RecyclerView.Adapter<ProductsBasketAdapter.ProductsBasketItemDesign>() {

    private val productsBasketList = ArrayList<ProductsModel>()
    var onRemoveBasketClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsBasketItemDesign {
        val productsBasketItemBinding = BasketItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsBasketItemDesign(productsBasketItemBinding)
    }

    override fun onBindViewHolder(holder: ProductsBasketItemDesign, position: Int) {
        holder.bind(productsBasketList[position])
    }

    inner class ProductsBasketItemDesign(private var productsBasketItemBinding: BasketItemBinding) :
        RecyclerView.ViewHolder(productsBasketItemBinding.root) {

        fun bind(productsbasket: ProductsModel) {

            productsBasketItemBinding.apply {

                productsModel= productsbasket

                productsbasket.image.let {
                    Picasso.get().load(it).into(ProductsBasketImageView)
                }

                productsBasketDelete.setOnClickListener {
                    onRemoveBasketClick(productsbasket.id)
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return productsBasketList.size
    }

    fun updateList(list: List<ProductsModel>) {
        productsBasketList.clear()
        productsBasketList.addAll(list)
        notifyDataSetChanged()
    }
}
