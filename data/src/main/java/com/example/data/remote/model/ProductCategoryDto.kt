package com.example.data.remote.model

import com.example.data.utils.DataMapper
import com.example.main.domain.model.ProductCategoryModel

data class ProductCategoryDto(
    val id: Int,
    val name: String,
    val image: String?
) : DataMapper<ProductCategoryModel> {

    override fun toDomain() = ProductCategoryModel(
        id = id,
        name = name,
        image = image,
    )
}