package com.renarosantos.ecommerceapp.data

import com.renarosantos.ecommerceapp.ui.ProductCardViewState

interface ProductRepository {

    suspend fun getProductList(): List<ProductCardViewState>
}