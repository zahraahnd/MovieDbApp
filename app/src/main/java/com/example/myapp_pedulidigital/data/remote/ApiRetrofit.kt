package com.example.myapp_pedulidigital.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiRetrofit {
    fun getRetrofitInstance(): Retrofit {
        val client = OkHttpClient.Builder()
            .connectTimeout(68L, TimeUnit.SECONDS)
            .readTimeout(68L, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiEndpoint.BASE_URL)
            .build()
    }
}