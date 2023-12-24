package com.example.main.presentation.ui.fragments.main

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.main.R
import com.example.main.databinding.FragmentMainBinding
import com.example.main.domain.model.ProductCategoryModel
import com.example.main.presentation.ui.adapters.ProductCategoryAdapter
import com.example.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainFragment :
    BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {

    override val binding by viewBinding(FragmentMainBinding::bind)
    override val viewModel: MainViewModel by viewModels()

    private var categories = emptyList<ProductCategoryModel>()
    private val categoryAdapter: ProductCategoryAdapter by lazy {
        ProductCategoryAdapter(categories, ::onItemClick)
    }

    override fun initialize() {
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = categoryAdapter
        }
    }

    override fun launchObservers() {
        productCategoriesObserver()
    }

    private fun productCategoriesObserver() {
        viewModel.productCategoriesState.spectateUiState(
            loading = {
                binding.progressCircular.show()
            },
            success = { data ->
                binding.progressCircular.hide()
                categoryAdapter.submitData(data)
            },
            error = {
                binding.progressCircular.hide()
            }
        )
    }

    private fun onItemClick(productId: Int) {
        findNavController()
            .navigate(MainFragmentDirections.actionMainFragmentToProductsFragment(productId))
    }
}