package com.example.main.domain.use_case

import com.example.main.domain.repository.MainRepository
import javax.inject.Inject

class GetProductCategoriesUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke() = repository.getProductCategoriesList()
}