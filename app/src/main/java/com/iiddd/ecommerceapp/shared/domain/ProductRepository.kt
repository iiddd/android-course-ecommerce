package com.iiddd.ecommerceapp.shared.domain

import com.iiddd.ecommerceapp.productdetails.domain.ProductDetails
import com.iiddd.ecommerceapp.productlist.domain.Product

interface ProductRepository {

    suspend fun getProductList(): List<Product>

    suspend fun getProductDetails(productId: String): ProductDetails
}