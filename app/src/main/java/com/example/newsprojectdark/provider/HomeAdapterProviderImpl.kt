package com.example.newsprojectdark.provider

import androidx.fragment.app.Fragment
import com.example.newsprojectdark.features.news.NewsFragment
import com.example.newsprojectdark.features.settings.SettingsFragment
import com.example.newsprojectdark.features.readlater.ReadLaterFragment
import com.example.newsprojectdark.provider.HomeAdapterProvider

class HomeAdapterProviderImpl : HomeAdapterProvider {
    override fun createNewsFragment(): Fragment {
        return NewsFragment.newInstance()
    }

    override fun createReadLaterFragment(): Fragment {
        return ReadLaterFragment.newInstance()
    }

    override fun createSettingsFragment(): Fragment {
        return SettingsFragment.newInstance()
    }
}