package com.example.reddittopapi.data.repository

import com.example.reddittopapi.data.dao.DaoPublication
import com.example.reddittopapi.data.entity.PublicationTable
import kotlinx.coroutines.flow.Flow

class DaoRepository(private val daoPublication: DaoPublication) {

    suspend fun add(publications: List<PublicationTable>) = daoPublication.add(publications)

    suspend fun deleteAll() = daoPublication.deleteAll()

    fun getTenPosts(): Flow<List<PublicationTable>> = daoPublication.getPosts()
}