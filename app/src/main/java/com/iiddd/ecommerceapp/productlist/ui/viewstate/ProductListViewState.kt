package com.iiddd.ecommerceapp.productlist.ui.viewstate

sealed class ProductListViewState {
    object Loading : ProductListViewState()
    data class Content(val productList: List<ProductCardViewState>) : ProductListViewState()
    data class Error(val errorMessage: String) : ProductListViewState()
}