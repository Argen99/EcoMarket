package com.example.main.presentation.ui.fragments.main

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.main.R
import com.example.main.databinding.FragmentMainBinding
import com.example.main.domain.model.ProductCategoryModel
import com.example.main.presentation.ui.adapters.ProductCategoryAdapter
import com.example.ui.base.BaseFragment
import com.example.ui.ui_state.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class MainFragment :
    BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {

    override val binding by viewBinding(FragmentMainBinding::bind)
    override val viewModel: MainViewModel by viewModels()

    private var categories = emptyList<ProductCategoryModel>()
    private val categoryAdapter: ProductCategoryAdapter by lazy {
        ProductCategoryAdapter(categories)
    }

    override fun initialize() {
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = categoryAdapter
        }
    }

    override fun launchObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productCategoriesState.collect {
                when(it) {
                    is UIState.Idle -> {}
                    is UIState.Loading -> {
                        binding.progressCircular.show()
                    }
                    is UIState.Success -> {
                        binding.progressCircular.hide()
                        categoryAdapter.submitData(it.data)
                    }
                    is UIState.Error -> {
                        binding.progressCircular.hide()
                    }
                }
            }
        }
    }
}