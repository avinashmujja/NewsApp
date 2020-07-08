package com.grabtaxi.android_tech_test_grab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.example.github.viewmodel.GrabViewModelFactory
import com.grabtaxi.android_tech_test_grab.viewmodel.NewsViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(userViewModel: NewsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: GrabViewModelFactory): ViewModelProvider.Factory
}
