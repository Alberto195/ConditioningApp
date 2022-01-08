package com.example.conditioning511.presentation.bottom_bar.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object Rooms: BottomBarScreen(
        title = "Rooms",
        route = "rooms",
        icon = Icons.Default.Info,
    )
    object Scripts: BottomBarScreen(
        title = "Scripts",
        route = "scripts_overall",
        icon = Icons.Default.Settings,
    )
    object Profile: BottomBarScreen(
        title = "Profile",
        route = "profile",
        icon = Icons.Default.Person,
    )
}
