package com.grabtaxi.androidtechnicaltest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.grabtaxi.android_tech_test_grab.model.NewsItems

@Database(entities = [NewsItems::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}