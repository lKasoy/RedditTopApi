package com.example.reddittopapi.data.repository

import android.util.Log
import android.widget.Toast
import com.example.reddittopapi.data.entity.PublicationTable
import com.example.reddittopapi.data.entity.PublicationTable.Companion.toDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class Repository(
    private val apiRepository: ApiRepository,
    private val daoRepository: DaoRepository
) {

    private var isFirstResponse: Boolean = true
    var after: String = "null"

    val posts: Flow<List<PublicationTable>> = daoRepository.getPosts()
    val post = MutableSharedFlow<PublicationTable>()


    suspend fun getTop() {
        try {
            val response = apiRepository.getTopPublications(after)
            val publications = toDatabase(response)
            if (isFirstResponse) {
                daoRepository.deleteAll()
                isFirstResponse = false
            }
            after = publications[0].after
            daoRepository.add(publications)
        } catch (e: Exception) {
            Log.d("test", e.toString())
        }
    }

    suspend fun getSomePost(id: String)  {
        post.emit(daoRepository.getSomePost(id))
    }

//    suspend fun getTopAfter() {
//        Log.d("test", "onEndReached")
//        val response = apiRepository.getTopAfter(after!!)
//        val publications = toDatabase(response)
//        daoRepository.add(publications)
//        after = publications[0].after
//    }
}