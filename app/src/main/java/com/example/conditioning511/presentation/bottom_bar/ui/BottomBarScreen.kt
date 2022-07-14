package com.example.conditioning511.presentation.bottom_bar.ui

import com.example.conditioning511.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
) {
    object Rooms : BottomBarScreen(
        title = "Комнаты",
        route = "rooms",
        icon = R.drawable.rooms_icon,
    )

    object Scripts : BottomBarScreen(
        title = "Сценарии",
        route = "scripts_overall",
        icon = R.drawable.scenarios_icon,
    )

    object Profile : BottomBarScreen(
        title = "Профиль",
        route = "profile",
        icon = R.drawable.profile_icon,
    )
}
