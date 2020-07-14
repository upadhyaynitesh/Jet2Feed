package com.example.jet2feed.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.jet2feed.R
import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("id") val id: Int,
    @SerializedName("blogId") val blogId: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("image") val image: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
) {
    companion object {
        /*This will load the image downloaded using Glide in imageView of recycler items*/
        @BindingAdapter("articleImage")
        @JvmStatic
        fun loadArticleImage(view: ImageView, articleImage: String) {
            Glide.with(view.context)  //2
                .load(articleImage) //3
                .centerCrop() //4
                .placeholder(R.drawable.ic_place_holder) //5
                .into(view)
        }
    }
}