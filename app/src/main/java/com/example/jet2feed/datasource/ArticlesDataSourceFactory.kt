package com.example.jet2feed.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.jet2feed.model.Articles

class ArticlesDataSourceFactory : DataSource.Factory<Int, Articles>() {
    val articlesLiveDataSource = MutableLiveData<ArticlesDataSource>()
    override fun create(): DataSource<Int, Articles> {
        val feedDataSource = ArticlesDataSource()
        articlesLiveDataSource.postValue(feedDataSource)
        return feedDataSource
    }
}