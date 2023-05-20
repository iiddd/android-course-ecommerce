package com.iiddd.ecommerceapp.productdetails.ui.viewstate

import com.iiddd.ecommerceapp.productlist.domain.Product

sealed class ProductDetailsViewState {

    object Loading : ProductDetailsViewState()
    data class Content(val product: Product) : ProductDetailsViewState()
    data class Error(val errorMessage: String) : ProductDetailsViewState()
}