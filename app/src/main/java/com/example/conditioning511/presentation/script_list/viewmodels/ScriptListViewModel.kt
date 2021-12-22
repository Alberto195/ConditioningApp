package com.example.conditioning511.presentation.script_list.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.conditioning511.presentation.script_list.states.SearchWidgetState

class ScriptListViewModel: ViewModel() {

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidget(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateTextWidget(newValue: String) {
        _searchTextState.value = newValue
    }
}