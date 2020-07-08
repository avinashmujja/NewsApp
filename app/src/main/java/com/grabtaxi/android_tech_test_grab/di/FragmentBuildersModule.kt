package com.grabtaxi.android_tech_test_grab

import com.grabtaxi.android_tech_test_grab.ui.NewsDetailsWebFragment
import com.grabtaxi.android_tech_test_grab.ui.NewsListFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeNewsListFragment(): NewsListFragment

    @ContributesAndroidInjector
    abstract fun contributeNewsDetailsWebFragment(): NewsDetailsWebFragment

}
