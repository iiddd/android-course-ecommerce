package com.iiddd.ecommerceapp.data

import com.iiddd.ecommerceapp.ui.ProductCardViewState

interface ProductRepository {

    suspend fun getProductList(): List<ProductCardViewState>
}