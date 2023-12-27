package com.example.data.local.room_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.ProductsDao
import com.example.data.local.model.ProductEntity

@Database(
    version = 1,
    entities = [ProductEntity::class]
)
abstract class ProductDataBase: RoomDatabase() {

    abstract fun getProductsDao(): ProductsDao
}