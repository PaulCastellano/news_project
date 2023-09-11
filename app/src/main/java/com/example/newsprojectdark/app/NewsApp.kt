package com.example.newsprojectdark.app

import com.example.framework.core.base.application.CoreApplication
import com.example.newsprojectdark.provider.AppProvider
import com.example.newsprojectdark.provider.ThemeProvider
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class NewsApp : CoreApplication<NewsAppConfig>() {

    @Inject
    lateinit var appProvider: AppProvider

    @Inject
    lateinit var themeProvider: ThemeProvider

    override fun appConfig(): NewsAppConfig {
        return NewsAppConfig()
    }

    override fun onCreate() {
        super.onCreate()
        initNightMode()
    }

    /**
     * Initialize Night Mode based on user last saved state (day/night themes).
     */
    private fun initNightMode() {
        appProvider.isNightMode = themeProvider.isDarkTheme(applicationContext)
        themeProvider.setNightMode(appProvider.isNightMode)
    }
}