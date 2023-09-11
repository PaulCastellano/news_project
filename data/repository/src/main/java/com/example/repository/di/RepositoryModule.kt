package com.example.repository.di

import com.example.local.dao.ReadLaterDao
import com.example.remote.service.ContentService
import com.example.repository.news.ContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNewsRepository(
        service: ContentService,
        dao: ReadLaterDao
    ) = ContentRepository(service, dao)
}