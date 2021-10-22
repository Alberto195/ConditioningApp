package com.example.conditioning511.presentation.core.viewmodels

import androidx.lifecycle.ViewModel
import com.example.conditioning511.domain.core.usecases.GetScriptDetailsUseCase
import com.example.conditioning511.domain.core.usecases.GetScriptGeneralInfoIseCase
import com.example.conditioning511.domain.core.usecases.InsertDetailedScriptUseCase
import com.example.conditioning511.domain.core.usecases.SetScriptGeneralInfoUseCase

class MainViewModel(
    private val getScriptDetailsUseCase: GetScriptDetailsUseCase,
    private val getScriptGeneralInfoIseCase: GetScriptGeneralInfoIseCase,
    private val insertDetailedScriptUseCase: InsertDetailedScriptUseCase,
    private val setScriptGeneralInfoUseCase: SetScriptGeneralInfoUseCase
) : ViewModel() {



}