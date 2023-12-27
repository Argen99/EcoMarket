package com.example.basket.presentation.ui.fragments.basket

import com.example.basket.domain.use_case.GetCartProductsUseCase
import com.example.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel()
class BasketViewModel @Inject constructor(
    private val getCartProductsUseCase: GetCartProductsUseCase
): BaseViewModel() {

}