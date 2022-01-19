package com.example.conditioning511.domain.core.usecases

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo
import com.example.conditioning511.domain.core.repositories.RoomDbRepository
import javax.inject.Inject

class InitScriptWorkerUseCase @Inject constructor(
    private val repository: @JvmSuppressWildcards RoomDbRepository
) {

    suspend fun execute(): LiveData<WorkInfo> {
        return repository.initScriptWorker()
    }

}