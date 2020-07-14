package com.example.jet2feed.database

import androidx.room.TypeConverter
import com.example.jet2feed.model.Media
import com.example.jet2feed.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MediaTypeConverter {

    /**
     * Convert a list of Media object to a Json
     */
    @TypeConverter
    fun fromString(value: String?): List<Media> {
        val listType: Type = object : TypeToken<List<Media>>() {}.type
        return Gson().fromJson(value, listType)
    }

    /**
     * Convert a json to a list of Media objects
     */
    @TypeConverter
    fun fromArrayList(list: List<Media>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}