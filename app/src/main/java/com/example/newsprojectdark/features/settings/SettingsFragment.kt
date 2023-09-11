package com.example.newsprojectdark.features.settings

import android.os.Bundle
import com.example.newsprojectdark.base.BaseFragment
import com.example.newsprojectdark.databinding.FragmentSettingsBinding
import com.example.newsprojectdark.provider.AppProvider
import com.example.newsprojectdark.provider.ThemeProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }

    @Inject
    lateinit var appProvider: AppProvider

    @Inject
    lateinit var themeProvider: ThemeProvider

    override fun onViewReady(bundle: Bundle?) {
        binding.tvAppVersion.text = "1.0.1"
        binding.switchThemeMode.isChecked = appProvider.isNightMode
    }

    override fun onViewListener() {
        super.onViewListener()
        binding.switchThemeMode.setOnCheckedChangeListener { _, isChecked ->
            appProvider.isNightMode = isChecked
            themeProvider.setNightMode(forceNight = appProvider.isNightMode)
        }
    }
}