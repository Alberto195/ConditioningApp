package com.example.conditioning511.presentation.bottom_bar.ui

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.ViewModelFactoryModules_ActivityModule_ProvideFactoryFactory.provideFactory
import androidx.hilt.lifecycle.ViewModelFactoryModules_FragmentModule_ProvideFactoryFactory.provideFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.conditioning511.presentation.rooms.ui.RoomsScreen
import com.example.conditioning511.presentation.rooms.viewmodels.RoomsScreenViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Rooms.route,
    ) {
        composable(route = BottomBarScreen.Rooms.route){
            val roomsViewModel: RoomsScreenViewModel = viewModel(
                factory = RoomsScreenViewModel.provideFactory()
            )
            RoomsScreen(roomsViewModel)
        }
        composable(route = BottomBarScreen.Scripts.route){
            //TODO add Scripts Screen
            println("Scripts")
        }
        composable(route = BottomBarScreen.Profile.route){
            //TODO add Profile Screen
            println("Profile")
        }
    }
}