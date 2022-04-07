package com.example.reddittopapi.data.repository

class Repository(private val apiRepository: ApiRepository) {

    suspend fun getResponse() = apiRepository.getResponse()
}