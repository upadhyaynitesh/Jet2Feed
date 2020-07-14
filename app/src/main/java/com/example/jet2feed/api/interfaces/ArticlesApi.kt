package com.example.jet2feed.api.interfaces

import com.example.jet2feed.model.Articles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {

    @GET("v1/blogs")
    fun fetchArticles(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int
    ): Call<List<Articles>>
}