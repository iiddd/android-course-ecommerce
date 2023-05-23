package com.iiddd.ecommerceapp.productlist.ui.viewstate

data class ProductCardViewState(
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val isFavorite: Boolean
)