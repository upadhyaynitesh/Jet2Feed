package com.example.jet2feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jet2feed.BR
import com.example.jet2feed.R
import com.example.jet2feed.databinding.ListItemArticlesBinding
import com.example.jet2feed.model.Articles

class RecyclerViewAdapter :
    PagedListAdapter<Articles, RecyclerViewAdapter.RecyclerViewHolder>(USER_COMPARATOR) {

    // Creating ViewHolder
    class RecyclerViewHolder(private val binding: ListItemArticlesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(articlesList: Articles) {
            binding.setVariable(
                BR.article,
                articlesList
            ) //BR - generated class; BR.article - 'article' is a variable name declared in layout
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListItemArticlesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.list_item_articles, parent, false
        )
        return RecyclerViewHolder(binding)
    }

    // Bind data
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val listItem = getItem(position)
        listItem?.let { holder.bind(it) }
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<Articles>() {
            override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean =
                newItem == oldItem
        }
    }
}