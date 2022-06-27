package com.example.quickup.data.main.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quickup.data.model.ProductsModel
import com.example.quickup.databinding.FavoritesItemBinding
import com.squareup.picasso.Picasso

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.FavoritesItemDesign>() {

    private val favoritesBasketList = ArrayList<ProductsModel>()
    var onRemoveFavoritesClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesItemDesign {
        val favoritesItemBinding = FavoritesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritesItemDesign(favoritesItemBinding)
    }

    override fun onBindViewHolder(holder: FavoritesItemDesign, position: Int) {
        holder.bind(favoritesBasketList[position])
    }

    inner class FavoritesItemDesign(private var favoritesItemBinding: FavoritesItemBinding) :
        RecyclerView.ViewHolder(favoritesItemBinding.root) {

        fun bind(productsfavorites: ProductsModel) {

            favoritesItemBinding.apply {

                productsModel= productsfavorites

                productsfavorites.image.let {
                    Picasso.get().load(it).into(ProductsFavoritesImageView)
                }

                productsFavoritesDelete.setOnClickListener {
                    onRemoveFavoritesClick(productsfavorites.id)
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return favoritesBasketList.size
    }

    fun updateList(list: List<ProductsModel>) {
        favoritesBasketList.clear()
        favoritesBasketList.addAll(list)
        notifyDataSetChanged()
    }
}