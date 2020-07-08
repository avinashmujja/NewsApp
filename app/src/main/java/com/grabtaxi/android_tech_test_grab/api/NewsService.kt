package com.grabtaxi.android_tech_test_grab.api

import androidx.lifecycle.LiveData
import com.grabtaxi.android_tech_test_grab.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    fun fetchTopHeadLines(@Query("country")  country :
                                  String,@Query("apiKey")  apiKey : String) : LiveData<ApiResponse<NewsResponse>>
}