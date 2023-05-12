package com.iiddd.ecommerceapp.data.repository.api

import com.iiddd.ecommerceapp.data.repository.ProductRepository
import com.iiddd.ecommerceapp.domain.entities.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryAPI
@Inject constructor(private val service: ProductService) : ProductRepository {

    override suspend fun getProductList(): List<Product> {
        return withContext(Dispatchers.IO) {
            service.getProductList().map {
                Product(
                    it.id,
                    it.title,
                    it.description,
                    "US $ ${it.price}",
                    it.imageUrl
                )
            }
        }
    }

    suspend fun getProduct(id: Int): Product {
        return getProductList().first { i -> i.id == id }
    }
}