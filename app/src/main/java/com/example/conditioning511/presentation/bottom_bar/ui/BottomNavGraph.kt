package com.example.conditioning511.presentation.bottom_bar.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Rooms.route,
    ) {
        composable(route = BottomBarScreen.Rooms.route){
            //TODO add Rooms Screen
            println("Rooms")
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