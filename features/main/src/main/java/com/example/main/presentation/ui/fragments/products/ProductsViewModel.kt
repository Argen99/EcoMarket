package com.example.main.presentation.ui.fragments.products

import com.example.main.domain.model.ProductCategoryModel
import com.example.main.domain.use_case.GetProductCategoriesUseCase
import com.example.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductCategoriesUseCase: GetProductCategoriesUseCase
): BaseViewModel() {

    private val _productCategoriesState = mutableUiStateFlow<List<ProductCategoryModel>>()
    val productCategoriesState = _productCategoriesState.asStateFlow()

    init {
        getProductCategories()
    }

    private fun getProductCategories() {
        getProductCategoriesUseCase().gatherRequest(_productCategoriesState)
    }
}