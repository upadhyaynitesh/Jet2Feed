package com.example.jet2feed.model

import com.google.gson.annotations.SerializedName

data class Articles(
    @SerializedName("id") val id: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("content") val content: String,
    @SerializedName("comments") val comments: Int,
    @SerializedName("likes") val likes: Int,
    @SerializedName("media") val media: List<Media>,
    @SerializedName("user") val user: List<User>
)