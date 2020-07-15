package com.example.jet2feed.datasource

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.jet2feed.interfaces.Listener
import com.example.jet2feed.model.Articles

class ArticlesDataSourceFactory(
    private val listener: Listener,
    private val hasInternet: Boolean,
    private val application: Context
) :
    DataSource.Factory<Int, Articles>() {
    val articlesLiveDataSource = MutableLiveData<ArticlesDataSource>()
    override fun create(): DataSource<Int, Articles> {
        val feedDataSource = ArticlesDataSource(listener, hasInternet, application)
        articlesLiveDataSource.postValue(feedDataSource)
        return feedDataSource
    }
}