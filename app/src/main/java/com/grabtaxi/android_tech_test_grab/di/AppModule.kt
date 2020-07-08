package com.grabtaxi.android_tech_test_grab

import android.app.Application
import androidx.room.Room
import com.grabtaxi.android_tech_test_grab.api.NewsService
import com.grabtaxi.android_tech_test_grab.api.LiveDataCallAdapterFactory
import com.grabtaxi.androidtechnicaltest.db.AppDatabase
import com.grabtaxi.androidtechnicaltest.db.NewsDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideGithubService(): NewsService {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(NewsService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "grabtestdata.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): NewsDao {
        return db.newsDao
    }

}
