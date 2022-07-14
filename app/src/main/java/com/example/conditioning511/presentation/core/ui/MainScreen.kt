package com.example.conditioning511.presentation.core.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.conditioning511.R
import com.example.conditioning511.presentation.bottom_bar.ui.BottomBarScreen
import com.example.conditioning511.presentation.bottom_bar.ui.BottomNavGraph
import com.example.conditioning511.presentation.rooms.viewmodels.RoomsScreenViewModel
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel

@Composable
fun MainScreen(roomsViewModel: RoomsScreenViewModel, scriptListViewModel: ScriptListViewModel) {
    val navController = rememberNavController()
    val bottomBarVisibility = remember { mutableStateOf(true) }
    Scaffold(
        bottomBar = { BottomBar(navController = navController, bottomBarVisibility) },
        backgroundColor = Color(0xFFF2F7F9),
    ) {
        BottomNavGraph(navController = navController, roomsViewModel, scriptListViewModel, bottomBarVisibility)
    }
}

@Composable
fun BottomBar(navController: NavHostController, bottomBarVisibility: MutableState<Boolean>) {
    val screens = listOf(
        BottomBarScreen.Rooms,
        BottomBarScreen.Scripts,
        BottomBarScreen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    if (bottomBarVisibility.value) {
        BottomNavigation(contentColor = Color(0xFF000000), backgroundColor = Color(0xFFFFFFFF)) {
            screens.forEach { screen ->
                AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = { 
            Icon(
                painterResource(id = screen.icon),
                contentDescription = "Navigation Icon",
                modifier = Modifier.size(20.dp)
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled)
    )
}