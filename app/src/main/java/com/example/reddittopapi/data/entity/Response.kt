package com.example.reddittopapi.data.entity

data class Response (
    val kind: String,
    val data: Data
)

data class Data(
    val children: List<Children>
)

data class Children(
    val data: ChildrenData
)

data class ChildrenData(
    val title: String,
    val thumbnail: String,
    val created: Long,
    val author: String,
    val num_comments: Long,
    val url: String
)