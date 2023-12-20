package com.example.main.presentation.ui.fragments.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.main.domain.model.ProductCategoryModel
import com.example.main.domain.repository.MainRepository
import com.example.main.domain.use_case.GetProductCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getProductCategoriesUseCase: GetProductCategoriesUseCase
): ViewModel() {

    private val _productCategoriesState = MutableStateFlow<List<ProductCategoryModel>>(emptyList())
    val productCategoriesState = _productCategoriesState.asStateFlow()

    init {
        viewModelScope.launch {
            getProductCategoriesUseCase().collect {
                _productCategoriesState.value = it
            }
        }
    }
}