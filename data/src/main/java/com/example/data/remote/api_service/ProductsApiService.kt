package com.example.data.remote.api_service

import com.example.data.remote.model.BasePagingResponse
import com.example.data.remote.model.ProductCategoryDto
import com.example.data.remote.model.ProductDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApiService {

    @GET(PRODUCT_CATEGORY_LIST)
    suspend fun getProductCategoriesList(): List<ProductCategoryDto>

    @GET(PRODUCT_LIST)
    suspend fun getProductList(
        @Query("category_name") categoryName: String? = null,
        @Query("search") search: String?,
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): BasePagingResponse<ProductDto>

    companion object {
        const val PRODUCT_CATEGORY_LIST = "product-category-list"
        const val PRODUCT_LIST = "product-list"
    }
}