package com.iiddd.ecommerceapp.data

import com.iiddd.ecommerceapp.domain.ProductEntity
import retrofit2.http.GET

interface ProductService {

    @GET("/products")
    suspend fun getProductList(): List<ProductEntity>
}