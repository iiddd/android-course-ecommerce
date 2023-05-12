package com.iiddd.ecommerceapp.data.repository.api

import com.iiddd.ecommerceapp.data.entities.ProductEntity
import retrofit2.http.GET

interface ProductService {

    @GET("/products")
    suspend fun getProductList(): List<ProductEntity>
}