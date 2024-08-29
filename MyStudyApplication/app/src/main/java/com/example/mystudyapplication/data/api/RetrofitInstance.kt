package com.example.mystudyapplication.data.api

import com.example.mystudyapplication.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(OkHttpClient().newBuilder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: BookSearchApi by lazy {
        retrofit.create(BookSearchApi::class.java)
    }
}