package com.renarosantos.ecommerceapp.domain

data class ProductEntity(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val imageUrl: String
)
