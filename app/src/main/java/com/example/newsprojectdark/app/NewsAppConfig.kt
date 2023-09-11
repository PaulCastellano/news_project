package com.example.newsprojectdark.app

import com.example.framework.core.base.application.CoreConfig

class NewsAppConfig : CoreConfig() {
    override fun appName(): String {
        return "News"
    }

    override fun baseUrl(): String {
        return "https://content.guardianapis.com/"
    }

    override fun timeOut(): Long {
        return 30L
    }
}