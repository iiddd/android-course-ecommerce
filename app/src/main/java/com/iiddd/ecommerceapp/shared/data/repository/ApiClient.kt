package com.iiddd.ecommerceapp.shared.data.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        fun getService(): ProductService {
            return Retrofit.Builder()
                .baseUrl("https://us-central1-android-course-api.cloudfunctions.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ProductService::class.java)
        }
    }
}