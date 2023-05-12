package com.iiddd.ecommerceapp.di

import com.iiddd.ecommerceapp.data.repository.api.ApiClient
import com.iiddd.ecommerceapp.data.repository.ProductRepository
import com.iiddd.ecommerceapp.data.repository.api.ProductService
import com.iiddd.ecommerceapp.data.repository.api.ProductRepositoryAPI
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