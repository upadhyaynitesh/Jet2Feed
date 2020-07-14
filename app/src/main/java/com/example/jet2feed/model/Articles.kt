package com.example.jet2feed.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jet2feed.utils.TimeUtils
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "article_records")
class Articles : Serializable {
    @PrimaryKey
    @NotNull
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: Int = 0

    @SerializedName("createdAt")
    @ColumnInfo(name = "createdAt")
    var createdAt: String = ""

    @SerializedName("content")
    @ColumnInfo(name = "content")
    var content: String = ""

    @SerializedName("comments")
    @ColumnInfo(name = "comments")
    var comments: Int = 0

    @SerializedName("likes")
    @ColumnInfo(name = "likes")
    var likes: Int = 0

    @SerializedName("media")
    @ColumnInfo(name = "media")
    var media: List<Media> = emptyList()

    @SerializedName("user")
    @ColumnInfo(name = "user")
    var user: List<User> = emptyList()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getArticleDate(): String {
        return TimeUtils.getTimeDifference(createdAt)
    }
}