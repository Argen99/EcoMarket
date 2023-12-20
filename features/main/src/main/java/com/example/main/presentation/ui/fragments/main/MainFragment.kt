package com.example.main.presentation.ui.fragments.main

import android.util.Log
import android.widget.GridLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.main.R
import com.example.main.databinding.FragmentMainBinding
import com.example.main.domain.model.ProductCategoryModel
import com.example.main.presentation.ui.adapters.ProductCategoryAdapter
import com.example.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(R.layout.fragment_main) {

    override val binding by viewBinding(FragmentMainBinding::bind)
    override val viewModel: MainViewModel by viewModels()

    override fun initialize() {
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun launchObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.productCategoriesState.collect {

            }
        }
    }
}