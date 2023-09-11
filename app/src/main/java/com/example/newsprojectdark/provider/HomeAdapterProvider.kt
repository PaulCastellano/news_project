package com.example.newsprojectdark.provider

import androidx.fragment.app.Fragment

interface HomeAdapterProvider {
    fun createNewsFragment(): Fragment

    fun createReadLaterFragment(): Fragment

    fun createSettingsFragment(): Fragment
}