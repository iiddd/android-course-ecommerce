package com.iiddd.ecommerceapp.productlist.ui.viewstate

import com.iiddd.ecommerceapp.productlist.ui.viewstate.ProductListViewState.Content

sealed class ProductListViewState {
    object Loading : ProductListViewState()
    data class Content(val productList: List<ProductCardViewState>) : ProductListViewState()
    data class Error(val errorMessage: String) : ProductListViewState()
}

fun Content.updateFavoriteProduct(
    productId: String,
    isFavorite: Boolean
): Content {
    return Content(productList = this.productList.map { viewState ->
        if (viewState.id == productId) {
            viewState.copy(isFavorite = isFavorite)
        } else {
            viewState
        }
    })
}