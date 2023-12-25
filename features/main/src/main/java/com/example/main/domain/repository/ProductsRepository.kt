package com.example.main.domain.repository

import androidx.paging.PagingData
import com.example.common.either.Either
import com.example.main.domain.model.ProductCategoryModel
import com.example.main.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun getProductCategoriesList() : Flow<Either<List<ProductCategoryModel>>>

    fun getProductsList(text: String?) : Flow<PagingData<ProductModel>>
}