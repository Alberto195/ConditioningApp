package com.example.conditioning511.presentation.bottom_bar.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.conditioning511.domain.script_list.models.RoomGroup
import com.example.conditioning511.presentation.rooms.ui.RoomsScreen
import com.example.conditioning511.presentation.rooms.viewmodels.RoomsScreenViewModel
import com.example.conditioning511.presentation.script_list.ui.ScriptDetails
import com.example.conditioning511.presentation.script_list.ui.ScriptListScreen
import com.example.conditioning511.presentation.script_list.ui.room_groups.DateGroupsScreen
import com.example.conditioning511.presentation.script_list.ui.room_groups.RoomGroupsScreen
import com.example.conditioning511.presentation.script_list.ui.room_groups.SettingsArgumentsScreen
import com.example.conditioning511.presentation.script_list.ui.room_groups.SettingsGroupScreen
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    roomsViewModel: RoomsScreenViewModel,
    scriptListViewModel: ScriptListViewModel,
    interactionSource: MutableState<Boolean>
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Rooms.route,
    ) {
        composable(route = BottomBarScreen.Rooms.route) {
            RoomsScreen(roomsViewModel)
        }
        navigation(startDestination = "scripts/overall", route = BottomBarScreen.Scripts.route) {
            composable("scripts/overall") {
                ScriptListScreen(navController, scriptListViewModel, interactionSource)
            }
            composable(
                "script/room_groups",
            ) {
                scriptListViewModel.getRoomGroupList()
                RoomGroupsScreen(scriptListViewModel, navController)
            }
            composable(
                "script/day_groups",
            ) {
                scriptListViewModel.getUpdate()
                DateGroupsScreen(scriptListViewModel, navController)
            }
            composable(
                "script/settings_groups",
            ) {
                scriptListViewModel.getUpdate()
                SettingsGroupScreen(scriptListViewModel, navController)
            }
            composable(
                "script/arguments",
            ) {
                scriptListViewModel.getUpdate()
                SettingsArgumentsScreen(scriptListViewModel, navController)
            }
        }
        composable(route = BottomBarScreen.Profile.route) {
            //TODO add Profile Screen
            println("Profile")
        }
    }
}
