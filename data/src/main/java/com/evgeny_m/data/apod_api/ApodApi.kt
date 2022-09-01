package com.evgeny_m.data.apod_api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApodApi {
    private const val APOD_URL = "https://api.nasa.gov/planetary/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(APOD_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}