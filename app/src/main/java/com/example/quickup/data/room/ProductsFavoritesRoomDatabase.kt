package com.example.quickup.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quickup.data.model.ProductsModel

@Database(entities = [ProductsModel::class], version = 1, )
abstract class ProductsFavoritesRoomDatabase : RoomDatabase() {

    abstract fun productsFavoritesDAOInterface(): ProductsFavoritesDAOInterface

    companion object {

        private var instance: ProductsFavoritesRoomDatabase? = null

        fun productsFavoritesRoomDatabase (context: Context): ProductsFavoritesRoomDatabase? {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    ProductsFavoritesRoomDatabase::class.java,
                    "productsfavoritesdatabase.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}