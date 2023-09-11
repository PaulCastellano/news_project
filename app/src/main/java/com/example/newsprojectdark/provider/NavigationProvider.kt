package com.example.newsprojectdark.provider

import androidx.fragment.app.Fragment

interface NavigationProvider {
    fun launchMainActivity()
    fun launchDetailFragment(fragment: Fragment, detailId: String)
}