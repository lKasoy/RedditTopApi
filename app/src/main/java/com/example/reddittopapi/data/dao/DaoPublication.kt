package com.example.reddittopapi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.reddittopapi.data.entity.PublicationTable
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoPublication {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(publications: List<PublicationTable>)

    @Query("DELETE FROM reddit_publications")
    fun deleteAll()

    @Query("SELECT * FROM reddit_publications")
    fun getPosts(): Flow<List<PublicationTable>>

    @Query("SELECT * FROM reddit_publications where thumbnailUrl =:id")
    fun getSomePost(id: String): PublicationTable


}