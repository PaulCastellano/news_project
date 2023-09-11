package com.example.newsprojectdark.features.home

import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.example.newsprojectdark.base.BaseFragment
import com.example.newsprojectdark.databinding.FragmentHomeBinding
import com.example.newsprojectdark.features.home.adapter.HomePage.Companion.toHomePageFromMenuItemId
import com.example.newsprojectdark.features.home.adapter.HomePage.Companion.toHomePageMenuItemIdFromPosition
import com.example.newsprojectdark.features.home.adapter.HomeViewPagerAdapter
import com.example.newsprojectdark.provider.HomeAdapterProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var homeAdapterProvider: HomeAdapterProvider

    override fun onViewReady(bundle: Bundle?) {
        val vpAdapter = HomeViewPagerAdapter(this, homeAdapterProvider)
        binding.vpMain.adapter = vpAdapter
        binding.vpMain.offscreenPageLimit = vpAdapter.itemCount
        binding.vpMain.isUserInputEnabled = false

        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val itemId = position.toHomePageMenuItemIdFromPosition()
                if (binding.bottomNavView.selectedItemId != itemId) {
                    binding.bottomNavView.selectedItemId = itemId
                }
            }
        })

        // set bottom nav
        binding.bottomNavView.setOnItemSelectedListener { item ->
            val position = item.toPagePosition()
            if (binding.vpMain.currentItem != position) {
                setItem(position)
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun MenuItem.toPagePosition(): Int {
        return itemId.toHomePageFromMenuItemId().position
    }

    private fun setItem(position: Int) {
        binding.vpMain.setCurrentItem(position, false)
    }

    override fun onDestroyView() {
        /*
            Without setting ViewPager2 Adapter it causes memory leak
            https://stackoverflow.com/questions/62851425/viewpager2-inside-a-fragment-leaks-after-replacing-the-fragment-its-in-by-navig
         */
        binding.vpMain.adapter = null
        super.onDestroyView()
    }
}