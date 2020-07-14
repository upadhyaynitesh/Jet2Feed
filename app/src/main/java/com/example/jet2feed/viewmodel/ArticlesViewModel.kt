package com.example.jet2feed.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.jet2feed.datasource.ArticlesDataSource
import com.example.jet2feed.datasource.ArticlesDataSourceFactory
import com.example.jet2feed.model.Articles

class ArticlesViewModel : ViewModel() {
    var userPagedList: LiveData<PagedList<Articles>>
    private var liveDataSource: LiveData<ArticlesDataSource>

    init {
        val itemDataSourceFactory = ArticlesDataSourceFactory()
        liveDataSource = itemDataSourceFactory.articlesLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ArticlesDataSource.PAGE_SIZE)
            .build()
        userPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }

}