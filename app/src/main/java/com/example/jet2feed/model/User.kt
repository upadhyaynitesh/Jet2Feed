package com.example.jet2feed.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.jet2feed.R
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("blogId") val blogId: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("name") val name: String,
    @SerializedName("avatar") val avatar: String,
    @SerializedName("lastname") val lastName: String,
    @SerializedName("city") val city: String,
    @SerializedName("designation") val designation: String,
    @SerializedName("about") val about: String
) {
    companion object {
        /*This will load the image downloaded using Glide in imageView of recycler items*/
        @BindingAdapter("profileImage")
        @JvmStatic
        fun loadProfileImage(view: ImageView, profileImage: String) {
            Glide.with(view.context)  //2
                .load(profileImage) //3
                .centerCrop() //4
                .placeholder(R.drawable.ic_place_holder) //5
                .into(view)
        }
    }
}