package com.example.newsprojectdark.provider

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.framework.extensions.launchActivity
import com.example.framework.navigation.AnimationType
import com.example.framework.navigation.navigateFragment
import com.example.newsprojectdark.features.detail.DetailFragment
import com.example.newsprojectdark.features.main.MainActivity
import com.example.newsprojectdark.provider.NavigationProvider

class NavigationProviderImpl(private val context: Context) : NavigationProvider {

    override fun launchMainActivity() {
        context.launchActivity<MainActivity> {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }

    override fun launchDetailFragment(fragment: Fragment, detailId: String) {
        val detailFragment = DetailFragment.newInstance(detailId = detailId)
        fragment.navigateFragment(fragment = detailFragment, animation = AnimationType.DEFAULT)
    }
}