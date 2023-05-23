package com.iiddd.ecommerceapp.productdetails.ui.viewstate

import com.iiddd.ecommerceapp.productdetails.domain.ProductDetails

sealed class ProductDetailsViewState {

    object Loading : ProductDetailsViewState()
    data class Content(val product: ProductDetails) : ProductDetailsViewState()
    data class Error(val errorMessage: String) : ProductDetailsViewState()
}