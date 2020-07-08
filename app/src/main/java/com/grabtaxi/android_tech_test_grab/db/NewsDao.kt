package com.grabtaxi.androidtechnicaltest.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.grabtaxi.android_tech_test_grab.model.NewsItems

@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsItem")
    fun fetchNewsFromDB() : LiveData<List<NewsItems>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsItems(newsList:  List<NewsItems>)

    @Query("DELETE FROM NewsItem")
    fun deleteAll()

}