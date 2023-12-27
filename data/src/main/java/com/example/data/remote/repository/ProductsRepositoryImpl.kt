package com.example.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.common.either.Either
import com.example.data.base.makeNetworkRequest
import com.example.data.local.dao.ProductsDao
import com.example.data.local.model.toData
import com.example.data.remote.api_service.ProductsApiService
import com.example.data.remote.paging_src.ProductsPagingSource
import com.example.main.domain.model.ProductCategoryModel
import com.example.main.domain.model.ProductModel
import com.example.main.domain.repository.ProductsRepository
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@BoundTo(supertype = ProductsRepository::class, component = SingletonComponent::class)
class ProductsRepositoryImpl @Inject constructor(
    private val productApiService: ProductsApiService,
    private val productsDao: ProductsDao
) : ProductsRepository {

    override fun getProductCategoriesList(): Flow<Either<List<ProductCategoryModel>>> =
        makeNetworkRequest {
            productApiService.getProductCategoriesList().map { it.toDomain() }
        }

    override fun getProductsList(
        text: String?,
        categoryName: String?
    ): Flow<PagingData<ProductModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ProductsPagingSource(productApiService, text, categoryName) }
        ).flow
    }

    override fun getCartProducts(): Flow<List<ProductModel>> {
        return productsDao.getProducts().map { it.map { it.toDomain() } }
    }

    override suspend fun addToCart(product: ProductModel) {
        productsDao.addProduct(product.toData())
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}