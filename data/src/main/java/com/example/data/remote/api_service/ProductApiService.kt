package com.example.data.remote.api_service

import com.example.data.remote.model.ProductCategoryDto
import retrofit2.http.GET

interface ProductApiService {

    @GET(PRODUCT_CATEGORY_LIST)
    suspend fun getProductCategoryList(): List<ProductCategoryDto>

    companion object {
        const val PRODUCT_CATEGORY_LIST = "product-category-list"
    }
}