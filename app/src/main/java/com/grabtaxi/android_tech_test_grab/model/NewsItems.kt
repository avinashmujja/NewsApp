package com.grabtaxi.android_tech_test_grab.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Newsitem")
data class NewsItems(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id : Int?,

    @ColumnInfo(name = "author")
    val author: String?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "url")
    val url: String?,

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String?,

    @ColumnInfo(name = "publishedAt")
    val publishedAt: String?,

    @ColumnInfo(name = "content")
    val content: String?)

data class NewsResponse(@SerializedName("articles")
                        @Expose
                        val newsList: List<NewsItems>,
                        @SerializedName("status")
                        @Expose
                        val status: String?)