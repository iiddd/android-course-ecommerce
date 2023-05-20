package com.iiddd.ecommerceapp.shared.data.repository

import com.iiddd.ecommerceapp.productlist.data.ProductEntity
import retrofit2.http.GET

interface ProductService {

    @GET("/products")
    suspend fun getProductList(): List<ProductEntity>
}