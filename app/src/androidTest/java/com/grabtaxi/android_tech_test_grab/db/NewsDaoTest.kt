package com.grabtaxi.android_tech_test_grab.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import com.grabtaxi.android_tech_test_grab.LiveDataTestUtil.getValue
import com.grabtaxi.android_tech_test_grab.TestUtil
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndReadNewsArticles() {
        val news = TestUtil.createNewsResponse()
        db.newsDao.insertNewsItems(news.newsList)
        val loaded = getValue(db.newsDao.fetchNewsFromDB())
        MatcherAssert.assertThat(loaded, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded[0].author, CoreMatchers.`is`("author1"))
        MatcherAssert.assertThat(loaded[0].content, CoreMatchers.`is`("content1"))
        MatcherAssert.assertThat(loaded[0].description, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(loaded[0].title, CoreMatchers.`is`("title1"))
    }
}