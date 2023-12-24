package com.example.main.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.main.databinding.ItemCategoryGridBinding
import com.example.main.domain.model.ProductCategoryModel
import com.example.ui.extensions.loadImageWithGlide

class ProductCategoryAdapter(
    private var categories: List<ProductCategoryModel>,
    private val onItemClick:(id: Int) -> Unit
) : Adapter<ProductCategoryAdapter.ProductCategoryViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(list: List<ProductCategoryModel>) {
        categories = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductCategoryViewHolder(
        ItemCategoryGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: ProductCategoryViewHolder, position: Int) {
        holder.onBind(categories[position])
    }

    inner class ProductCategoryViewHolder(private val binding: ItemCategoryGridBinding) :
        ViewHolder(binding.root) {
        fun onBind(item: ProductCategoryModel) = with(binding) {
            ivCategoryImage.loadImageWithGlide(item.image)
            tvCategoryTitle.text = item.name
        }

        init {
            binding.root.setOnClickListener {
                onItemClick(categories[adapterPosition].id)
            }
        }
    }
}