package com.renarosantos.ecommerceapp.data

import com.renarosantos.ecommerceapp.domain.ProductEntity
import retrofit2.http.GET

interface ProductService {

    @GET("/products")
    suspend fun getProductList(): List<ProductEntity>
}