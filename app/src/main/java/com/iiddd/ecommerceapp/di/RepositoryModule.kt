package com.iiddd.ecommerceapp.di

import android.content.Context
import androidx.room.Room
import com.iiddd.ecommerceapp.shared.data.repository.api.ApiClient
import com.iiddd.ecommerceapp.shared.data.repository.api.ProductRepositoryAPI
import com.iiddd.ecommerceapp.shared.data.repository.api.ProductService
import com.iiddd.ecommerceapp.shared.domain.ProductRepository
import com.iiddd.ecommerceapp.wishlist.data.repository.WishListSharedPreferencesRepository
import com.iiddd.ecommerceapp.wishlist.data.repository.WishlistDatabaseRepository
import com.iiddd.ecommerceapp.wishlist.data.repository.WishlistRepository
import com.iiddd.ecommerceapp.wishlist.data.repository.database.AppDatabase
import com.iiddd.ecommerceapp.wishlist.data.repository.database.WishlistDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    fun providesWishlistRepository(
        databaseRepository: WishlistDatabaseRepository
    ): WishlistRepository = databaseRepository

    @Provides
    fun providesWishlistDatabaseRepository(
        databaseDAO: WishlistDAO
    ): WishlistDatabaseRepository {
        return WishlistDatabaseRepository(databaseDAO)
    }

    @Provides
    fun providesWishlistSharedPreferencesRepository(
        @ApplicationContext context: Context
    ): WishListSharedPreferencesRepository {
        return WishListSharedPreferencesRepository(context)
    }

    @Provides
    fun providesWishlistDAO(
        @ApplicationContext context: Context
    ): WishlistDAO {
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "ecommerce-db").build()
        return db.wishListDao()
    }
}