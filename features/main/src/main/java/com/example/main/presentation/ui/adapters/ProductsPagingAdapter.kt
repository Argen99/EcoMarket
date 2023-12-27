package com.example.main.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.main.databinding.ItemProductBinding
import com.example.main.domain.model.ProductModel
import com.example.ui.extensions.loadImageWithGlide

class ProductsPagingAdapter(
    private val onItemClick: (id: Int) -> Unit,
    private val onPlusClick: (product: ProductModel) -> Unit,
    private val onMinusClick: (product: ProductModel) -> Unit,
) :
    PagingDataAdapter<ProductModel, ProductsPagingAdapter.ProductsViewHolder>(diffCallBack) {

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductsViewHolder(
        ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    inner class ProductsViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(product: ProductModel) = with(binding) {
            ivProductImage.loadImageWithGlide(product.image)
            tvProductPrice.text = product.price
            tvProductTitle.text = product.title
        }
        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { onItemClick(it.id) }
            }
            binding.btnAdd.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { onPlusClick(it) }
            }
            binding.btnMinus.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { onMinusClick(it) }
            }
        }
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<ProductModel>() {
            override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}