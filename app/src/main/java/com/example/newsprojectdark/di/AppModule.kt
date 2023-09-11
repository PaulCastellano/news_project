package com.example.newsprojectdark.di

import com.example.framework.core.base.application.CoreConfig
import com.example.newsprojectdark.app.NewsApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApplication(): NewsApp {
        return NewsApp()
    }

    @Provides
    @Singleton
    fun provideAppConfig(app: NewsApp): CoreConfig {
        return app.appConfig()
    }
}