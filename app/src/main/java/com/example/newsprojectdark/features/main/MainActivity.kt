package com.example.newsprojectdark.features.main

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.framework.extensions.showSnackBar
import com.example.framework.navigation.navigateFragment
import com.example.newsprojectdark.R
import com.example.newsprojectdark.base.BaseActivity
import com.example.newsprojectdark.databinding.ActivityMainBinding
import com.example.newsprojectdark.features.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private var backPressedOnce = false

    override fun onViewReady(bundle: Bundle?) {
        navigateFragment(HomeFragment.newInstance(), clearBackStack = true)
    }

    override fun moveTaskToBack(nonRoot: Boolean): Boolean {
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.moveTaskToBack(nonRoot)
        } else {
            if (backPressedOnce) {
                finish()
                return false
            }
            backPressedOnce = true
            showSnackBar(
                binding.rootView,
                getString(R.string.app_exit_label),
                R.id.bottomNavView
            )
            lifecycleScope.launch {
                delay(2000)
                backPressedOnce = false
            }
        }
        return super.moveTaskToBack(nonRoot)
    }
}