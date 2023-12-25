package com.example.main.presentation.ui.fragments.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.main.domain.model.ProductCategoryModel
import com.example.main.domain.model.ProductModel
import com.example.main.domain.use_case.GetProductCategoriesUseCase
import com.example.main.domain.use_case.GetProductsListUseCase
import com.example.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductCategoriesUseCase: GetProductCategoriesUseCase,
    private val getProductsListUseCase: GetProductsListUseCase
) : BaseViewModel() {

    private val _productCategoriesState = mutableUiStateFlow<List<ProductCategoryModel>>()
    val productCategoriesState = _productCategoriesState.asStateFlow()

    private val searchBy = MutableLiveData("")
    private val filterByCategory = MutableStateFlow("")

    val test: Flow<PagingData<ProductModel>>

    init {
        getProductCategories()
        test = searchBy.asFlow()
            .debounce(500)
            .flatMapLatest {
                getProductsListUseCase(it)
            }
            .cachedIn(viewModelScope)
    }

    private fun getProductCategories() {
        getProductCategoriesUseCase().gatherRequest(_productCategoriesState)
    }

    fun search(text: String) {
        searchBy.value = text
    }
}