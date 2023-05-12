package com.iiddd.ecommerceapp.domain

import com.iiddd.ecommerceapp.data.ProductRepository
import com.iiddd.ecommerceapp.data.ProductService
import com.iiddd.ecommerceapp.ui.ProductCardViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryAPI
@Inject constructor(private val service: ProductService) : ProductRepository {

    override suspend fun getProductList(): List<ProductCardViewState> {
        return withContext(Dispatchers.IO) {
            service.getProductList().map {
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