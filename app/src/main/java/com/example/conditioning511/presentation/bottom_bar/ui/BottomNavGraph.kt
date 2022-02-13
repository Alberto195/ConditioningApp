package com.example.conditioning511.presentation.bottom_bar.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.conditioning511.domain.script_list.models.RoomGroups
import com.example.conditioning511.presentation.rooms.ui.RoomsScreen
import com.example.conditioning511.presentation.rooms.viewmodels.RoomsScreenViewModel
import com.example.conditioning511.presentation.script_list.ui.ScriptDetails
import com.example.conditioning511.presentation.script_list.ui.ScriptListScreen
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    roomsViewModel: RoomsScreenViewModel,
    scriptListViewModel: ScriptListViewModel
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
                ScriptListScreen(navController, scriptListViewModel)
            }
            composable(
                "script/{details}",
            ) { backStackEntry ->
                val scriptValueEncoded = backStackEntry.arguments?.getString("details")
                val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
                val jsonAdapter = moshi.adapter(RoomGroups::class.java).lenient()
                val scriptValues = jsonAdapter.fromJson(scriptValueEncoded)
                ScriptDetails(scriptValues, scriptListViewModel)
            }
        }
        composable(route = BottomBarScreen.Profile.route) {
            //TODO add Profile Screen
            println("Profile")
        }
    }
}
