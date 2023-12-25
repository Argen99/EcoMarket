package com.example.main.domain.model

data class ProductModel(
    val id: Int,
    val title: String,
    val description: String,
    val category: Int,
    val image: String?,
    val price: String
)
