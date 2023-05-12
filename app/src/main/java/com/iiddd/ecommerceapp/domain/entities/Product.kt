package com.iiddd.ecommerceapp.domain.entities

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String
)