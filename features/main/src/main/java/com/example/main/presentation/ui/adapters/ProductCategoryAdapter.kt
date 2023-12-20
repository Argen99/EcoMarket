package com.example.main.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.main.databinding.ItemCategoryBinding
import com.example.main.domain.model.ProductCategoryModel
import com.example.ui.extensions.loadImageWithGlide

class ProductCategoryAdapter(
    private val categories: List<ProductCategoryModel>
) : Adapter<ProductCategoryAdapter.ProductCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductCategoryViewHolder(
        ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: ProductCategoryViewHolder, position: Int) {
        holder.onBind(categories[position])
    }

    inner class ProductCategoryViewHolder(private val binding: ItemCategoryBinding) : ViewHolder(binding.root) {
        fun onBind(item: ProductCategoryModel) = with(binding) {
            ivCategoryImage.loadImageWithGlide(item.image)
            tvCategoryTitle.text = item.name
        }
    }
}