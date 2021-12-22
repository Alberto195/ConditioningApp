package com.example.conditioning511.presentation.bottom_bar.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.ViewModelFactoryModules_ActivityModule_ProvideFactoryFactory.provideFactory
import androidx.hilt.lifecycle.ViewModelFactoryModules_FragmentModule_ProvideFactoryFactory.provideFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.conditioning511.presentation.rooms.ui.RoomsScreen
import com.example.conditioning511.presentation.rooms.viewmodels.RoomsScreenViewModel
import com.example.conditioning511.presentation.script_list.ui.ScriptListScreen
import kotlinx.coroutines.launch

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
            ScriptListScreen(navController)
        }
        composable(route = BottomBarScreen.Profile.route){
            //TODO add Profile Screen
            println("Profile")
        }
    }
}