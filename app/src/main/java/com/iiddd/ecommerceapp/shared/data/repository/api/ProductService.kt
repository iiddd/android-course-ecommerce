package com.iiddd.ecommerceapp.shared.data.repository.api

import com.iiddd.ecommerceapp.productdetails.data.ProductDetailsEntity
import com.iiddd.ecommerceapp.productlist.data.ProductEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("/products")
    suspend fun getProductList(): List<ProductEntity>

    @GET("productDetails")
    suspend fun getProductDetails(@Query("productId") productId: String): ProductDetailsEntity
}