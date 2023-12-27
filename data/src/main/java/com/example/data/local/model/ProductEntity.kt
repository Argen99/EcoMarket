package com.example.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.utils.DataMapper
import com.example.main.domain.model.ProductModel

@Entity(
    tableName = "products"
)
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val category: Int,
    val image: String?,
    val price: String,
    val count: Int?
) : DataMapper<ProductModel> {

    override fun toDomain() = ProductModel(
        id = id,
        title = title,
        description = description,
        category = category,
        image = image,
        price = price,
        count = count
    )
}

fun ProductModel.toData() = ProductEntity(
    id = id,
    title = title,
    description = description,
    category = category,
    image = image,
    price = price,
    count = count
)