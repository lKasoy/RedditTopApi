package com.example.reddittopapi.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reddit_publications")
data class PublicationTable (
    val author: String,
    val title: String,
    @PrimaryKey
    val date: Long,
//    val thumbnailUrl: String,
//    val fullScreenUrl: String,
    val numberOfComments: Long,
) {
    companion object {
        fun toDatabase(response: Response): List<PublicationTable> {
            return response.data.children.map {
                PublicationTable(
                    author = it.data.author,
                    title = it.data.title,
                    date = it.data.created,
                    numberOfComments = it.data.num_comments
                )
            }
        }
    }
}