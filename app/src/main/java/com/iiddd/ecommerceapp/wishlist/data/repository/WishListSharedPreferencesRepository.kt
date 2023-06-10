package com.iiddd.ecommerceapp.wishlist.data.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import javax.inject.Inject

class WishListSharedPreferencesRepository @Inject constructor(
    private val context: Context
) : WishlistRepository {
    override suspend fun isFavorite(productId: String): Boolean {
        val favorite = getSharedPreferences().getString(productId, "false")
        return !favorite.equals("false")
    }

    override suspend fun addToWishlist(productId: String) {
        getSharedPreferences().edit().putString(productId, productId).apply()
    }

    override suspend fun removeFromWishlist(productId: String) {
        getSharedPreferences().edit().remove(productId).apply()
    }

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("wishlistPrefs", MODE_PRIVATE)
    }
}