package com.example.reddittopapi.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reddittopapi.constants.Constants.COMMENTS_TXT
import com.example.reddittopapi.constants.Constants.DAYS_AGO_TXT
import com.example.reddittopapi.constants.Constants.HOURS_AGO_TXT
import com.example.reddittopapi.constants.Constants.HOUR_AGO_TXT
import com.example.reddittopapi.constants.Constants.K_COMMENTS_TXT
import com.example.reddittopapi.constants.Constants.LESS_THAN_ONE_HOUR_TXT
import com.example.reddittopapi.constants.Constants.POSTED_BY_TXT
import java.util.*
import kotlin.math.roundToInt

@Entity(tableName = "reddit_publications")
data class PublicationTable(
    val after: String,
    val author: String,
    val title: String,
    val passedTime: String,
    @PrimaryKey
    val thumbnailUrl: String,
    val fullScreenUrl: String,
    val numberOfComments: String,
    val isVideo: Boolean,
) {
    companion object {
        fun toDatabase(response: Response): List<PublicationTable> {
            return response.data.children.map {
                PublicationTable(
                    after = response.data.after,
                    author = POSTED_BY_TXT + it.data.author + " ",
                    title = it.data.title,
                    passedTime = dateToHoursPassed(it.data.created),
                    thumbnailUrl = it.data.thumbnail,
                    fullScreenUrl = it.data.url,
                    numberOfComments = convertComments(it.data.num_comments),
                    isVideo = it.data.is_video,
                )
            }
        }

        private fun dateToHoursPassed(date: Long): String {
            val cal = Calendar.getInstance()
            return when (val hours = ((cal.timeInMillis / 1000 - date) / 3600).toInt()) {
                0 -> {
                    LESS_THAN_ONE_HOUR_TXT
                }
                1 -> {
                    hours.toString() + HOUR_AGO_TXT
                }
                in 2..23 -> {
                    hours.toString() + HOURS_AGO_TXT
                }
                else -> {
                    (hours / 24).toString() + DAYS_AGO_TXT
                }
            }
        }

        private fun convertComments(numberOfComments: Long): String {
            return when (numberOfComments) {
                in 0..999 -> {
                    numberOfComments.toString() + COMMENTS_TXT
                }
                else -> {
                    ((numberOfComments / 100.0).roundToInt() / 10.0).toString() + K_COMMENTS_TXT
                }
            }
        }
    }
}
