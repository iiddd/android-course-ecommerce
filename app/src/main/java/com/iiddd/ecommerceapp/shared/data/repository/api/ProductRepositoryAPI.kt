package com.iiddd.ecommerceapp.shared.data.repository.api

import com.iiddd.ecommerceapp.productdetails.domain.ProductDetails
import com.iiddd.ecommerceapp.productlist.domain.Product
import com.iiddd.ecommerceapp.shared.domain.ProductRepository
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

    override suspend fun getProductDetails(productId: String): ProductDetails {
        return withContext(Dispatchers.IO) {
            service.getProductDetails(productId).run {
                ProductDetails(
                    this.title,
                    this.description,
                    this.full_description,
                    "US $ ${this.price}",
                    this.imageUrl,
                    this.pros,
                    this.cons
                )
            }
        }
    }
}