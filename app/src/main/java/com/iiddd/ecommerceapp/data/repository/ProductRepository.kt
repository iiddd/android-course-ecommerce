package com.iiddd.ecommerceapp.data.repository

import com.iiddd.ecommerceapp.domain.entities.Product

interface ProductRepository {

    suspend fun getProductList(): List<Product>
}