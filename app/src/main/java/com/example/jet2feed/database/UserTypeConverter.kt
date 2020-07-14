package com.example.jet2feed.database

import androidx.room.TypeConverter
import com.example.jet2feed.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class UserTypeConverter {

    /**
     * Convert a list of User object to a Json
     */
    @TypeConverter
    fun fromString(value: String?): List<User> {
        val listType: Type = object : TypeToken<List<User>>() {}.type
        return Gson().fromJson(value, listType)
    }

    /**
     * Convert a json to a list of User objects
     */
    @TypeConverter
    fun fromArrayList(list: List<User>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}