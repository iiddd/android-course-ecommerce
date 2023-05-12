package com.iiddd.ecommerceapp.ui.viewstate

import com.iiddd.ecommerceapp.domain.entities.Product

sealed class ProductDetailsViewState {

    object Loading : ProductDetailsViewState()
    data class Content(val product: Product) : ProductDetailsViewState()
    data class Error(val errorMessage: String) : ProductDetailsViewState()
}