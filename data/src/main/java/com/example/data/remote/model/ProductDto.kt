package com.example.data.remote.model

import com.example.data.utils.DataMapper
import com.example.main.domain.model.ProductModel

data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val category: Int,
    val image: String?,
    val price: String
): DataMapper<ProductModel> {

    override fun toDomain() = ProductModel(
        id = id,
        title = title,
        description = description,
        category = category,
        image = image,
        price = price
    )
}