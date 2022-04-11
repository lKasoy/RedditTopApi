package com.example.reddittopapi.data.repository

import com.example.reddittopapi.data.dao.DaoPublication
import com.example.reddittopapi.data.entity.PublicationTable
import kotlinx.coroutines.flow.Flow

class DaoRepository(private val daoPublication: DaoPublication) {

    fun add(publications: List<PublicationTable>) = daoPublication.add(publications)

    fun deleteAll() = daoPublication.deleteAll()

    fun getPosts(): Flow<List<PublicationTable>> = daoPublication.getPosts()

    fun getSomePost(id: String) = daoPublication.getSomePost(id)
}