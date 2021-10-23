package com.example.conditioning511.presentation.core.viewmodels

import androidx.lifecycle.ViewModel
import com.example.conditioning511.domain.core.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getScriptDetailsUseCase: GetScriptDetailsUseCase,
    private val getScriptGeneralInfoIseCase: GetScriptGeneralInfoIseCase,
    private val insertDetailedScriptUseCase: InsertDetailedScriptUseCase,
    private val setScriptGeneralInfoUseCase: SetScriptGeneralInfoUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val setUserUseCase: SetUserUseCase,
    private val initScriptWorkerUseCase: InitScriptWorkerUseCase
) : ViewModel() {



}