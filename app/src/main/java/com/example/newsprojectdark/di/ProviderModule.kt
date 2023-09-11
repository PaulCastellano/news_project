package com.example.newsprojectdark.di

import android.content.Context
import com.example.newsprojectdark.provider.AppProvider
import com.example.newsprojectdark.provider.AppProviderImpl
import com.example.newsprojectdark.provider.HomeAdapterProvider
import com.example.newsprojectdark.provider.HomeAdapterProviderImpl
import com.example.newsprojectdark.provider.NavigationProvider
import com.example.newsprojectdark.provider.NavigationProviderImpl
import com.example.newsprojectdark.provider.ResourceProvider
import com.example.newsprojectdark.provider.ResourceProviderImpl
import com.example.newsprojectdark.provider.ThemeProvider
import com.example.newsprojectdark.provider.ThemeProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProviderModule {
    @Provides
    @Singleton
    fun provideNavigationProviderImpl(@ApplicationContext context: Context): NavigationProvider {
        return NavigationProviderImpl(context)
    }

    @Provides
    @Singleton
    fun provideResourceProviderImpl(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProviderImpl(context)
    }

    @Provides
    @Singleton
    fun provideThemeProviderImpl(): ThemeProvider {
        return ThemeProviderImpl()
    }

    @Provides
    @Singleton
    fun provideAppProviderImpl(@ApplicationContext context: Context): AppProvider {
        return AppProviderImpl(context)
    }

    @Provides
    @Singleton
    fun provideHomeAdapterProviderImpl(): HomeAdapterProvider {
        return HomeAdapterProviderImpl()
    }
}