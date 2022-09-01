package com.evgeny_m.data.apod_api

import com.evgeny_m.data.model.ApodData
import com.evgeny_m.data.model.DataList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("apod")
    suspend fun getImageData(
        @Query("api_key") api_key: String,
        @Query("date") date: String,
    ): Response<ApodData>

    @GET("apod")
    suspend fun getArrayImagesData(
        @Query("api_key") api_key: String,
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String,
    ): Response<List<ApodData>>


}