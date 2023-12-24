package com.example.data.remote.repository

import com.example.common.either.Either
import com.example.data.base.makeNetworkRequest
import com.example.data.remote.api_service.ProductsApiService
import com.example.main.domain.model.ProductCategoryModel
import com.example.main.domain.repository.ProductsRepository
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@BoundTo(supertype = ProductsRepository::class, component = SingletonComponent::class)
class ProductsRepositoryImpl @Inject constructor(
    private val productApiService: ProductsApiService
) : ProductsRepository {

    override fun getProductCategoriesList(): Flow<Either<List<ProductCategoryModel>>> = makeNetworkRequest {
        productApiService.getProductCategoriesList().map { it.toDomain() }
    }
}