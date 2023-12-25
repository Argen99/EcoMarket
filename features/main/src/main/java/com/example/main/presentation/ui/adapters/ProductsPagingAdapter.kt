package com.example.main.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.main.databinding.ItemProductBinding
import com.example.main.domain.model.ProductModel
import com.example.ui.extensions.loadImageWithGlide

class ProductsPagingAdapter :
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

class CharacterPagingAdapter(
    private val onItemCLick: (id: String) -> Unit,
) : PagingDataAdapter<ProductModel, CharacterPagingAdapter.CharacterViewHolder>(diffCallBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CharacterViewHolder(
        ItemProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CharacterViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(character: ProductModel) = with(binding) {
            ivProductImage.loadImageWithGlide(character.image)
            tvProductPrice.text = character.title
        }

        init {
            itemView.setOnClickListener {
                onItemCLick(getItem(absoluteAdapterPosition)?.id.toString())
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