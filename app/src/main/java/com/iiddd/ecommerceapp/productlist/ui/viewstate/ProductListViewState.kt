package com.iiddd.ecommerceapp.productlist.ui.viewstate

import com.iiddd.ecommerceapp.productlist.domain.Product

sealed class ProductListViewState {
    object Loading : ProductListViewState()
    data class Content(val productList: List<Product>) : ProductListViewState()
    data class Error(val errorMessage: String) : ProductListViewState()
}