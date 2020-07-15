package com.example.jet2feed.activities

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jet2feed.R
import com.example.jet2feed.adapter.RecyclerViewAdapter
import com.example.jet2feed.viewmodel.ArticlesViewModel
import kotlinx.android.synthetic.main.activity_articles.*

class ArticlesActivity : AppCompatActivity() {

    private lateinit var viewModel: ArticlesViewModel
    private lateinit var articlesAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_action_bar)

        viewModel = ViewModelProvider(this).get(ArticlesViewModel::class.java)

        setArticleListAdapter()

        viewModel.fetchArticles()
        viewModel.articlesPagedList.observe(this, Observer {
            articlesAdapter.submitList(it)
        })
    }

    private fun setArticleListAdapter() {
        articlesAdapter = RecyclerViewAdapter()
        recyclerViewArticle.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewArticle.adapter = articlesAdapter
    }
}