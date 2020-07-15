package com.example.jet2feed.interfaces

import com.example.jet2feed.model.Articles

interface Listener {
    fun setArticles(articleList: List<Articles>)
}