package com.example.reddittopapi.data.network

import com.example.reddittopapi.data.entity.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top.json")
    suspend fun getResponse(): Response
}