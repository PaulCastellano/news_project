package com.example.domain.di

import com.example.domain.usacase.content.GetAllContent
import com.example.domain.usacase.content.GetContentDetail
import com.example.domain.usacase.readlater.AddReadLater
import com.example.domain.usacase.readlater.DeleteReadLater
import com.example.domain.usacase.readlater.GetReadLater
import com.example.domain.usacase.readlater.UpdateReadLater
import com.example.repository.news.ContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Singleton
    @Provides
    fun provideGetAllContent(repository: ContentRepository) = GetAllContent(repository)

    @Singleton
    @Provides
    fun provideGetContentDetail(repository: ContentRepository) = GetContentDetail(repository)

    @Singleton
    @Provides
    fun provideGetReadLater(repository: ContentRepository) = GetReadLater(repository)

    @Singleton
    @Provides
    fun provideAddReadLater(repository: ContentRepository) = AddReadLater(repository)

    @Singleton
    @Provides
    fun provideDeleteReadLater(repository: ContentRepository) = DeleteReadLater(repository)

    @Singleton
    @Provides
    fun provideUpdateReadLater(repository: ContentRepository) = UpdateReadLater(repository)
}