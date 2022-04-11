package com.example.reddittopapi.data.repository

import com.example.reddittopapi.data.network.ApiService

class ApiRepository(private val apiService: ApiService) {

//    suspend fun getTopPublications() = apiService.getTop(10)

    suspend fun getTopPublications(after: String) = apiService.getTopPublications(after = after, 10)
}

