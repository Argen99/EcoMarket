package com.example.data.remote.repository

import com.example.data.remote.api_service.ProductApiService
import com.example.main.domain.model.ProductCategoryModel
import com.example.main.domain.repository.MainRepository
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@BoundTo(supertype = MainRepository::class, component = SingletonComponent::class)
class MainRepositoryImpl @Inject constructor(
    private val productApiService: ProductApiService
) : MainRepository {

    override fun getProductCategoriesList(): Flow<List<ProductCategoryModel>> = flow {
        val result = productApiService.getProductCategoriesList().map { it.toDomain() }
        emit(result)
    }.flowOn(Dispatchers.IO)
}