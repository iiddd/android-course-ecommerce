package com.iiddd.ecommerceapp.shared.data.repository

import com.iiddd.ecommerceapp.productlist.domain.Product

interface ProductRepository {

    suspend fun getProductList(): List<Product>
}