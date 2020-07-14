package com.example.jet2feed.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jet2feed.model.Articles

@Database(entities = [Articles::class], version = 1, exportSchema = false)
@TypeConverters(MediaTypeConverter::class, UserTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticlesDao
}