package com.example.reddittopapi.data.repository

import com.example.reddittopapi.data.network.ApiService

class ApiRepository(private val apiService: ApiService) {

    suspend fun getResponse() = apiService.getResponse()
}

