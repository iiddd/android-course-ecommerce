package com.iiddd.ecommerceapp.productlist.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iiddd.ecommerceapp.R
import com.iiddd.ecommerceapp.databinding.ProductCardBinding
import com.iiddd.ecommerceapp.productlist.ui.viewstate.ProductCardViewState

class ProductCardListAdapter(
    private val onProductClickListener: (ProductCardViewState) -> Unit,
    private val onFavoriteIconClicked: (ProductCardViewState) -> Unit
) : RecyclerView.Adapter<ProductCardListAdapter.ViewHolder>() {


    private var data: List<ProductCardViewState> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)
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
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(productCardViewState: ProductCardViewState) {
            val binding = ProductCardBinding.bind(itemView)
            itemView.setOnClickListener {
                onProductClickListener(productCardViewState)
            }
            binding.apply {
                viewProductName.text = productCardViewState.title
                viewProductDescription.text = productCardViewState.description
                productPrice.text = productCardViewState.price
                viewWishlistButton.setOnClickListener {
                    onFavoriteIconClicked.invoke(productCardViewState)
                }
                viewWishlistButton.setImageDrawable(
                    if (productCardViewState.isFavorite) {
                        ResourcesCompat.getDrawable(
                            viewWishlistButton.resources,
                            R.drawable.ic_baseline_favorite,
                            null
                        )
                    } else {
                        ResourcesCompat.getDrawable(
                            viewWishlistButton.resources,
                            R.drawable.ic_baseline_favorite_disabled,
                            null
                        )
                    }
                )
                Glide.with(productImage)
                    .load(productCardViewState.imageUrl)
                    .into(productImage)
            }
        }
    }
}