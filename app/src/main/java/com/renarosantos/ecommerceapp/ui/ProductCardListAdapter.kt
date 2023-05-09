package com.renarosantos.ecommerceapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.renarosantos.ecommerceapp.R
import com.renarosantos.ecommerceapp.databinding.ProductCardBinding
import kotlin.properties.Delegates

class ProductCardListAdapter(
    private val onProductClickListener: (Int) -> Unit
) : RecyclerView.Adapter<ProductCardListAdapter.ViewHolder>() {


    private var data: List<ProductCardViewState> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false),
            onProductClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(productList: List<ProductCardViewState>) {
        this.data = productList
    }

    class ViewHolder(
        itemView: View,
        onProductClickListener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private var productId by Delegates.notNull<Int>()

        fun bind(productCardViewState: ProductCardViewState) {
            val binding = ProductCardBinding.bind(itemView)
            productId = productCardViewState.id
            binding.apply {
                viewProductName.text = productCardViewState.title
                viewProductDescription.text = productCardViewState.description
                productPrice.text = productCardViewState.price
                Glide.with(productImage)
                    .load(productCardViewState.imageUrl)
                    .into(productImage)
            }
        }

        init {
            itemView.setOnClickListener {
                onProductClickListener(productId)
            }
        }
    }
}