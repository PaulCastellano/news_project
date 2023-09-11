package com.example.newsprojectdark.features.home.adapter

import com.example.newsprojectdark.R

enum class HomePage(
    val position: Int,
    val menuItemId: Int
) {
    NEWS(
        position = 0,
        menuItemId = R.id.newsFragment
    ),
    READ_LATER(
        position = 1,
        menuItemId = R.id.readLaterFragment
    ),
    SETTINGS(
        position = 2,
        menuItemId = R.id.settingsFragment
    );

    internal companion object {

        fun Int.toHomePageFromPosition(): HomePage {
            return values().find { it.position == this }
                ?: throw IllegalArgumentException("Could not find the main page for the specified position: $this.")
        }

        fun Int.toHomePageMenuItemIdFromPosition(): Int {
            return values().find { it.position == this }?.menuItemId
                ?: throw IllegalArgumentException("Could not find the main page for the specified position: $this.")
        }

        fun Int.toHomePageFromMenuItemId(): HomePage {
            return values().find { it.menuItemId == this }
                ?: throw IllegalArgumentException("Could not find the main page for the specified menu item ID: $this.")
        }
    }
}