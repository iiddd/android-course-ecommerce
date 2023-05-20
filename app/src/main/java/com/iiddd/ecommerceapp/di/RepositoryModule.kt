package com.iiddd.ecommerceapp.di

import com.iiddd.ecommerceapp.shared.data.repository.ApiClient
import com.iiddd.ecommerceapp.shared.data.repository.ProductRepository
import com.iiddd.ecommerceapp.shared.data.repository.ProductService
import com.iiddd.ecommerceapp.shared.data.repository.ProductRepositoryAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providesProductService(): ProductService = ApiClient.getService()

    @Provides
    fun providesProductRepositoryAPI(
        service: ProductService
    ): ProductRepositoryAPI = ProductRepositoryAPI(service)

    @Provides
    fun providesProductRepository(
        productRepositoryAPI: ProductRepositoryAPI
    ): ProductRepository = productRepositoryAPI
}