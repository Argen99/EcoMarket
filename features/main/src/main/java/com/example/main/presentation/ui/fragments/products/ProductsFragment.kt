package com.example.main.presentation.ui.fragments.products

import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.main.R
import com.example.main.databinding.FragmentProductsBinding
import com.example.main.presentation.ui.adapters.CharacterPagingAdapter
import com.example.ui.base.BaseFragment
import com.example.ui.extensions.setPadding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment :
    BaseFragment<FragmentProductsBinding, ProductsViewModel>(R.layout.fragment_products) {

    override val binding by viewBinding(FragmentProductsBinding::bind)
    override val viewModel by viewModels<ProductsViewModel>()

    private val navArgs by navArgs<ProductsFragmentArgs>()

    private val productsAdapter: CharacterPagingAdapter by lazy {
        CharacterPagingAdapter({})
    }

    override fun initialize() {
        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter = productsAdapter
        }
    }

    override fun setupListeners() {
        binding.svProducts.setOnQueryTextFocusChangeListener { _, focused ->
            binding.tvCancel.isVisible = focused
            binding.svProducts.queryHint = if (focused) "" else getString(R.string.search_hint)
        }
        binding.svProducts.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                callSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                callSearch(newText)
                return true
            }

            fun callSearch(query: String?) {
                query?.let { viewModel.search(it) }
            }
        })
    }

    override fun launchObservers() {
        viewModel.productCategoriesState.spectateUiState(
            success = { categories ->
                categories.forEach { item ->
                    binding.rgCategories.addView(createCategory(item.name))
                }
            }
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getProduct().collect {
                productsAdapter.submitData(it)
            }
        }
    }

    private fun createCategory(name: String): RadioButton {
        val verticalPadding = resources.getDimensionPixelSize(R.dimen.radio_btn_vertical_padding)
        val horizontalPadding = resources.getDimensionPixelSize(R.dimen.radio_btn_horizontal_padding)

        val params = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            marginStart = resources.getDimensionPixelSize(R.dimen.radio_btn_margin_between_items)
        }

        val radioButton = RadioButton(requireContext()).apply {
            text = name
            setBackgroundResource(R.drawable.bg_linear_category)
            buttonDrawable = null
            setTextColor(
                ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.radio_btn_text_selector
                )
            )
            setPadding(verticalPadding, horizontalPadding)
            layoutParams = params
        }
        return radioButton
    }
}