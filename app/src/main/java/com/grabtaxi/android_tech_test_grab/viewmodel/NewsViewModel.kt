package com.grabtaxi.android_tech_test_grab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.grabtaxi.android_tech_test_grab.model.NewsItems
import com.grabtaxi.android_tech_test_grab.repository.NewsRepository
import com.grabtaxi.android_tech_test_grab.util.AbsentLiveData
import com.grabtaxi.android_tech_test_grab.util.Resource
import javax.inject.Inject


open class NewsViewModel @Inject constructor(newsRepository: NewsRepository) : ViewModel() {

    private val _country = MutableLiveData<String>()

    private val country : LiveData<String> = _country

    open val results: LiveData<Resource<List<NewsItems>>> = Transformations.switchMap(country) {input ->
        if(input.isNullOrEmpty()) {
            AbsentLiveData.create()
        } else{
            newsRepository.fetchNews(input)
        }
    }

    fun setCountry(string: String) {
        _country.value = string
    }



}