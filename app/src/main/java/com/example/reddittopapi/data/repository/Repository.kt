package com.example.reddittopapi.data.repository

import android.util.Log
import com.example.reddittopapi.data.entity.PublicationTable
import com.example.reddittopapi.data.entity.PublicationTable.Companion.toDatabase
import kotlinx.coroutines.flow.Flow

class Repository(
    private val apiRepository: ApiRepository,
    private val daoRepository: DaoRepository
) {

    private var isFirstResponse: Boolean = true

    val posts: Flow<List<PublicationTable>> = daoRepository.getPosts()

    suspend fun getResponse() {
        try {
            val response = apiRepository.getRedditTopPublications()
            val publications = toDatabase(response)
            if (isFirstResponse) {
                daoRepository.deleteAll()
                isFirstResponse = false
            }
            daoRepository.add(publications)
        } catch (e: Exception) {
            Log.d("test", e.toString())
        }
    }
}