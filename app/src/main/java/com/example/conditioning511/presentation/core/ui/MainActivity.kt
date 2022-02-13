package com.example.conditioning511.presentation.core.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.conditioning511.presentation.core.viewmodels.MainViewModel
import com.example.conditioning511.presentation.rooms.viewmodels.RoomsScreenViewModel
import com.example.conditioning511.presentation.script_list.viewmodels.ScriptListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null
    private val roomsViewModel: RoomsScreenViewModel by viewModels()
    private val scriptListViewModel: ScriptListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContent {
            MainScreen(roomsViewModel, scriptListViewModel)
        }
    }
}