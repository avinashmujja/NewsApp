package com.grabtaxi.android_tech_test_grab.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.grabtaxi.android_tech_test_grab.TestUtil
import com.grabtaxi.android_tech_test_grab.api.ApiResponse
import com.grabtaxi.android_tech_test_grab.api.NewsService
import com.grabtaxi.android_tech_test_grab.util.mock
import com.grabtaxi.android_tech_test_grab.model.NewsItems
import com.grabtaxi.android_tech_test_grab.model.NewsResponse
import com.grabtaxi.android_tech_test_grab.util.*
import com.grabtaxi.androidtechnicaltest.db.AppDatabase
import com.grabtaxi.androidtechnicaltest.db.NewsDao
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import retrofit2.Response

@RunWith(JUnit4::class)
class NewsRepositoryTest {
    private lateinit var repository: NewsRepository
    private val dao = Mockito.mock(NewsDao::class.java)
    private val service = Mockito.mock(NewsService::class.java)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val db = Mockito.mock(AppDatabase::class.java)
        Mockito.`when`(db.newsDao).thenReturn(dao)
        Mockito.`when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = NewsRepository(InstantAppExecutors(), dao, service)
    }

    @Test
    fun loadNewsFromDB() {
        val observer =
            mock<Observer<Resource<List<NewsItems>>>>()
        val dbNewsResult = MutableLiveData<List<NewsItems>>()
        //  val newsItems = MutableLiveData<List<NewsItems>>()
        Mockito.`when`(dao.fetchNewsFromDB()).thenReturn(dbNewsResult)

        repository.fetchNews("foo").observeForever(observer)

        Mockito.verify(observer).onChanged(Resource.loading(null))
        Mockito.verifyNoMoreInteractions(service)
        Mockito.reset(observer)


        val dbResult = TestUtil.createNewsItems(
            5,
            "author",
            "title",
            "description",
            "url",
            "urlToImage",
            "publishedAt",
            "content"
        )
        Mockito.`when`(dao.fetchNewsFromDB()).thenReturn(dbNewsResult)

        dbNewsResult.postValue(dbResult)

        // newsItems.postValue(dbResult)
        Mockito.verify(observer).onChanged(Resource.success(dbResult))
        Mockito.verifyNoMoreInteractions(service)
    }


    @Test
    fun search_fromServer() {

        val observer =
            mock<Observer<Resource<List<NewsItems>>>>()
        val dbSearchResult = MutableLiveData<List<NewsItems>>()
        //  val repositories = MutableLiveData<List<NewsItems>>()

        val apiResponse = TestUtil.createNewsResponse()

        val callLiveData = MutableLiveData<ApiResponse<NewsResponse>>()
        Mockito.`when`(service.fetchTopHeadLines(Constant.country_US,Constant.API_KEY)).thenReturn(callLiveData)

        Mockito.`when`(dao.fetchNewsFromDB()).thenReturn(dbSearchResult)

        repository.fetchNews("foo").observeForever(observer)

        Mockito.verify(observer).onChanged(Resource.loading(null))
        Mockito.verifyNoMoreInteractions(service)
        Mockito.reset(observer)

        Mockito.`when`(dao.fetchNewsFromDB()).thenReturn(dbSearchResult)
        dbSearchResult.postValue(apiResponse.newsList)
        val updatedResult = MutableLiveData<List<NewsItems>>()
        Mockito.`when`(dao.fetchNewsFromDB()).thenReturn(updatedResult)
        updatedResult.postValue(apiResponse.newsList)

        callLiveData.postValue(ApiResponse.create(Response.success(apiResponse)))
        dbSearchResult.postValue(apiResponse.newsList)
        Mockito.verify(observer).onChanged(Resource.success(apiResponse.newsList))
        Mockito.verifyNoMoreInteractions(service)
    }

}