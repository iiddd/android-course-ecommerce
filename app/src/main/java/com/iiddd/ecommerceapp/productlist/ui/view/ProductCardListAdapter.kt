package com.iiddd.ecommerceapp.productlist.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iiddd.ecommerceapp.R
import com.iiddd.ecommerceapp.databinding.ProductCardBinding
import com.iiddd.ecommerceapp.productlist.domain.Product
import kotlin.properties.Delegates

class ProductCardListAdapter(
    private val onProductClickListener: (Int) -> Unit
) : RecyclerView.Adapter<ProductCardListAdapter.ViewHolder>() {


    private var data: List<Product> = emptyList()

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

    fun setData(productList: List<Product>) {
        this.data = productList
    }

    class ViewHolder(
        itemView: View,
        onProductClickListener: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private var productId by Delegates.notNull<Int>()

        fun bind(product: Product) {
            val binding = ProductCardBinding.bind(itemView)
            productId = product.id
            binding.apply {
                viewProductName.text = product.title
                viewProductDescription.text = product.description
                productPrice.text = product.price
//                viewWishlistButton.setImageDrawable(
//                    if (prod) {
//
//                    }
//                )
                Glide.with(productImage)
                    .load(product.imageUrl)
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