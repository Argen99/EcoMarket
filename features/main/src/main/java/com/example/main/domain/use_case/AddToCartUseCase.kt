package com.example.main.domain.use_case

import com.example.main.domain.model.ProductModel
import com.example.main.domain.repository.ProductsRepository
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val repository: ProductsRepository
) {
    suspend operator fun invoke(product: ProductModel) = repository.addToCart(product)
}