package com.example.jet2feed.api.activities

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_action_bar)

        val viewModel = ViewModelProvider(this).get(ArticlesViewModel::class.java)
        val articlesAdapter = RecyclerViewAdapter()
        recyclerViewArticle.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewArticle.adapter = articlesAdapter

        viewModel.userPagedList.observe(this, Observer {
            articlesAdapter.submitList(it)
        })
    }
}