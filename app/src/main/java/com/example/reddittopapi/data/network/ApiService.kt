package com.example.reddittopapi.data.network

import com.example.reddittopapi.data.entity.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("top.json")
    suspend fun getTop(
        @Query("limit") limit: Int
    ): Response

    @GET("top.json")
    suspend fun getTopPublications(
        @Query("after") after: String,
        @Query("limit") limit: Int
    ): Response


}