package com.example.quickup.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "productsfavoritesdatabase")
data class ProductsModel (
        @PrimaryKey(autoGenerate = true)
        @SerializedName("id")
        @ColumnInfo(name = "id")
        var id: Int,
        @SerializedName("user")
        @ColumnInfo(name = "user")
        var user: String?,
        @SerializedName("title")
        @ColumnInfo(name = "title")
        var title: String,
        @SerializedName("price")
        @ColumnInfo(name = "price")
        var price: Double?,
        @SerializedName("description")
        @ColumnInfo(name = "description")
        var description: String,
        @SerializedName("category")
        @ColumnInfo(name = "category")
        var category: String,
        @SerializedName("image")
        @ColumnInfo(name = "image")
        var image: String?,
        @SerializedName("rate")
        @ColumnInfo(name = "rate")
        var rate: Double,
        @SerializedName("count")
        @ColumnInfo(name = "count")
        var count: Int,
        @SerializedName("sale_state")
        @ColumnInfo(name ="sale_state")
        var saleState: Int,

        )