package com.example.conditioning511.presentation.script_list.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel

@Composable
fun ScriptListScreen(navController: NavController, viewModel: ScriptListViewModel) {
    Column {
        ScriptList(navController = navController, viewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun ScriptListScreenPreview() {
    val navController = rememberNavController()
    ScriptListScreen(navController = navController, viewModel = viewModel<ScriptListViewModel>())
}