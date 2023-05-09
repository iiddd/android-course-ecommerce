package com.renarosantos.ecommerceapp.ui

sealed class ProductDetailsViewState {

    object Loading : ProductDetailsViewState()
    data class Content(val product: ProductCardViewState) : ProductDetailsViewState()
    data class Error(val errorMessage: String) : ProductDetailsViewState()
}