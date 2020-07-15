package com.example.jet2feed.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.Room
import com.example.jet2feed.database.AppDatabase
import com.example.jet2feed.datasource.ArticlesDataSource
import com.example.jet2feed.datasource.ArticlesDataSourceFactory
import com.example.jet2feed.interfaces.Listener
import com.example.jet2feed.model.Articles
import com.example.jet2feed.utils.Constants
import com.example.jet2feed.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ArticlesViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var articlesPagedList: LiveData<PagedList<Articles>>
    private val itemDataSourceFactory: ArticlesDataSourceFactory
    private var config: PagedList.Config
    private var liveDataSource: LiveData<ArticlesDataSource>

    init {
        val listener = object : Listener {
            override fun setArticles(articleList: List<Articles>) {
                GlobalScope.launch(Dispatchers.IO) {
                    val db: AppDatabase = Room.databaseBuilder(
                        application,
                        AppDatabase::class.java,
                        Constants.DATABASE_NAME
                    ).build()
                    db.articlesDao().insertArticles(articleList)
                }
            }
        }
        itemDataSourceFactory =
            ArticlesDataSourceFactory(listener, NetworkUtils.isInternetOn(application), application)
        liveDataSource = itemDataSourceFactory.articlesLiveDataSource
        config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ArticlesDataSource.PAGE_SIZE)
            .build()
    }

    fun fetchArticles() {
        articlesPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }
}