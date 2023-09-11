package com.example.newsprojectdark.features.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsprojectdark.features.home.adapter.HomePage.Companion.toHomePageFromPosition
import com.example.newsprojectdark.provider.HomeAdapterProvider

class HomeViewPagerAdapter(
    fragment: Fragment,
    private val homeAdapterProvider: HomeAdapterProvider
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return HomePage.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position.toHomePageFromPosition()) {
            HomePage.NEWS -> homeAdapterProvider.createNewsFragment()
            HomePage.READ_LATER -> homeAdapterProvider.createReadLaterFragment()
            HomePage.SETTINGS -> homeAdapterProvider.createSettingsFragment()
        }
    }
}