package com.iiddd.ecommerceapp.wishlist.data.repository

import com.iiddd.ecommerceapp.wishlist.data.repository.database.FavoriteProductEntity
import com.iiddd.ecommerceapp.wishlist.data.repository.database.WishlistDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WishlistDatabaseRepository @Inject constructor(
    private val dataBaseDao: WishlistDAO
) : WishlistRepository {
    override suspend fun isFavorite(productId: String): Boolean {
        return withContext(Dispatchers.IO) {
            dataBaseDao.isProductFavorite(productId) != null
        }
    }

    override suspend fun addToWishlist(productId: String) {
        return withContext(Dispatchers.IO) {
            dataBaseDao.addProductToFavorites(FavoriteProductEntity(productId, ""))
        }
    }

    override suspend fun removeFromWishlist(productId: String) {
        return withContext(Dispatchers.IO) {
            dataBaseDao.deleteProductFromEntities(FavoriteProductEntity(productId, ""))
        }
    }
}