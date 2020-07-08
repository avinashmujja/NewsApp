package com.grabtaxi.android_tech_test_grab

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.grabtaxi.android_tech_test_grab.model.NewsItems
import com.grabtaxi.android_tech_test_grab.repository.NewsRepository
import com.grabtaxi.android_tech_test_grab.util.Constant
import com.grabtaxi.android_tech_test_grab.util.Resource
import com.grabtaxi.android_tech_test_grab.util.mock
import com.grabtaxi.android_tech_test_grab.viewmodel.NewsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class NewsViewModelTest {

    @Rule
    @JvmField
    val instantExecutor = InstantTaskExecutorRule()

    private val repository = Mockito.mock(NewsRepository::class.java)
    private lateinit var viewModel: NewsViewModel

    @Before
    fun init() {
        viewModel = NewsViewModel(repository)
    }

    @Test
    fun empty() {
        viewModel.results.observeForever(mock())
        Mockito.verifyNoMoreInteractions(repository)
    }

    @Test
    fun basic() {
        viewModel.results.observeForever(mock())
        viewModel.setCountry("foo")
        Mockito.verify(repository).fetchNews("foo")
    }

    @Test
    fun noObserverNoCountry() {
        Mockito.`when`(repository.fetchNews("foo")).thenReturn(mock())
        viewModel.setCountry("foo")
        Mockito.verify(repository, Mockito.never()).fetchNews("foo")
    }
}