package com.example.main.domain.repository

import com.example.common.either.Either
import com.example.main.domain.model.ProductCategoryModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getProductCategoriesList() : Flow<Either<List<ProductCategoryModel>>>
}