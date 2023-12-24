package com.example.main.domain.use_case

import com.example.main.domain.repository.ProductsRepository
import javax.inject.Inject

class GetProductCategoriesUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    operator fun invoke() = repository.getProductCategoriesList()
}