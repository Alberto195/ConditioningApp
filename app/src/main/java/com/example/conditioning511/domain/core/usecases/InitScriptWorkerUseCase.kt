package com.example.conditioning511.domain.core.usecases

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo
import com.example.conditioning511.domain.core.repositories.ScriptListRepository

class InitScriptWorkerUseCase(
    private val repository: ScriptListRepository
) {

    suspend fun execute(): LiveData<WorkInfo> {
        return repository.initScriptWorker()
    }

}