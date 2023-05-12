package com.iiddd.ecommerceapp.ui.viewstate

import com.iiddd.ecommerceapp.domain.entities.Product

sealed class ProductListViewState {
    object Loading : ProductListViewState()
    data class Content(val productList: List<Product>) : ProductListViewState()
    data class Error(val errorMessage: String) : ProductListViewState()
}