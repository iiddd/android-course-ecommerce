package com.renarosantos.ecommerceapp.domain

import com.renarosantos.ecommerceapp.data.ApiClient
import com.renarosantos.ecommerceapp.data.ProductRepository
import com.renarosantos.ecommerceapp.ui.ProductCardViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepositoryAPI : ProductRepository {

    private val client = ApiClient().getClient()

    override suspend fun getProductList(): List<ProductCardViewState> {
        return withContext(Dispatchers.IO) {
            client.getProductList().map {
                ProductCardViewState(
                    it.id,
                    it.title,
                    it.description,
                    "US $ ${it.price}",
                    it.imageUrl
                )
            }
        }
    }

    suspend fun getProduct(id: Int): ProductCardViewState {
        return getProductList().first { i -> i.id == id }
    }
}