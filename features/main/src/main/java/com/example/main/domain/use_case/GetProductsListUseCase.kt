package com.example.main.domain.use_case

import com.example.main.domain.repository.ProductsRepository
import javax.inject.Inject

class GetProductsListUseCase @Inject constructor(
    private val productsRepository: ProductsRepository
) {
    operator fun invoke(text: String?, categoryName: String?) =
        productsRepository.getProductsList(text, categoryName)
}