package com.example.main.presentation.ui.fragments.products

import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.marginStart
import androidx.core.view.setPadding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.main.R
import com.example.main.databinding.FragmentProductsBinding
import com.example.ui.base.BaseFragment
import com.example.ui.extensions.setPadding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : BaseFragment<FragmentProductsBinding, ProductsViewModel>(R.layout.fragment_products) {

    override val binding by viewBinding(FragmentProductsBinding::bind)
    override val viewModel by viewModels<ProductsViewModel>()

    private val navArgs by navArgs<ProductsFragmentArgs>()

    override fun initialize() {
        Toast.makeText(requireContext(), navArgs.productId.toString(), Toast.LENGTH_SHORT).show()
        viewModel.productCategoriesState.spectateUiState(
            success = { categories ->
                categories.forEach { item ->

                    val verticalPadding = resources.getDimensionPixelSize(R.dimen.radio_btn_vertical_padding)
                    val horizontalPadding = resources.getDimensionPixelSize(R.dimen.radio_btn_horizontal_padding)

                    val params = ViewGroup.MarginLayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        marginStart = resources.getDimensionPixelSize(R.dimen.radio_btn_margin_between_items)
                    }

                    val radioButton = RadioButton(requireContext()).apply {
                        text = item.name
                        setBackgroundResource(R.drawable.bg_linear_category)
                        buttonDrawable = null
                        setTextColor(ContextCompat.getColorStateList(requireContext(), com.example.ui.R.color.radio_btn_text_selector))
                        setPadding(verticalPadding,horizontalPadding)
                        layoutParams = params
                    }
                    binding.rgCategories.addView( radioButton )
                }
            }
        )
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
                if (query.isNullOrEmpty()) {

                }
            }
        })
    }
}