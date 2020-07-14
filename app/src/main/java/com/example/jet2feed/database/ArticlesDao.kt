package com.example.jet2feed.database

import androidx.room.*
import com.example.jet2feed.model.Articles

@Dao
interface ArticlesDao {

    @Query("SELECT * FROM article_records")
    fun retrieveArticles(): List<Articles>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArticles(articlesList: List<Articles>)

    @Query("DELETE FROM article_records")
    fun deleteArticles()
}