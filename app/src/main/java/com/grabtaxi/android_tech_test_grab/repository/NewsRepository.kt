package com.grabtaxi.android_tech_test_grab.repository

import androidx.lifecycle.LiveData
import com.grabtaxi.android_tech_test_grab.AppExecutors
import com.grabtaxi.android_tech_test_grab.api.ApiSuccessResponse
import com.grabtaxi.android_tech_test_grab.api.NewsService
import com.grabtaxi.android_tech_test_grab.model.NewsItems
import com.grabtaxi.android_tech_test_grab.model.NewsResponse
import com.grabtaxi.android_tech_test_grab.util.Constant
import com.grabtaxi.android_tech_test_grab.util.Resource
import com.grabtaxi.androidtechnicaltest.db.NewsDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class NewsRepository @Inject constructor(private val appExecutors: AppExecutors,
                                              private val newsDao: NewsDao,
                                              private val newsService: NewsService) {
    open fun fetchNews(input : String): LiveData<Resource<List<NewsItems>>> {
        return object : NetworkBoundResource<List<NewsItems>, NewsResponse>(appExecutors) {

            override fun saveCallResult(item: NewsResponse) {
//                newsDao.deleteAll()
                newsDao.insertNewsItems(item.newsList)
            }

            override fun shouldFetch(data: List<NewsItems>?) = data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<NewsItems>> {
                return newsDao.fetchNewsFromDB()
            }

            override fun createCall() = newsService.fetchTopHeadLines(input,Constant.API_KEY)

            override fun processResponse(response: ApiSuccessResponse<NewsResponse>)
                    : NewsResponse {
                val body = response.body
                return body
            }
        }.asLiveData()
    }

}