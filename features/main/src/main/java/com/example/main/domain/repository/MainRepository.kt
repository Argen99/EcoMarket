package com.example.main.domain.repository

import com.example.main.domain.model.ProductCategoryModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

interface MainRepository {

    fun getProductCategoryList() : Flow<List<ProductCategoryModel>>
}