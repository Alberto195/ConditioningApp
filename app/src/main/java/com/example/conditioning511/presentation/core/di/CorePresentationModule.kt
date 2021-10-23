package com.example.conditioning511.presentation.core.di

import com.example.conditioning511.domain.core.repositories.ScriptListRepository
import com.example.conditioning511.domain.core.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CorePresentationModule {

    @Singleton
    @Provides
    fun provideGetScriptDetailsUseCase(
        repository: ScriptListRepository
    ): GetScriptDetailsUseCase = GetScriptDetailsUseCase(repository)

    @Singleton
    @Provides
    fun provideGetScriptGeneralInfoIseCase(
        repository: ScriptListRepository
    ): GetScriptGeneralInfoIseCase = GetScriptGeneralInfoIseCase(repository)

    @Singleton
    @Provides
    fun provideGetUserUseCase(
        repository: ScriptListRepository
    ): GetUserUseCase = GetUserUseCase(repository)

    @Singleton
    @Provides
    fun provideInsertDetailedScriptUseCase(
        repository: ScriptListRepository
    ): InsertDetailedScriptUseCase = InsertDetailedScriptUseCase(repository)

    @Singleton
    @Provides
    fun provideSetScriptGeneralInfoUseCase(
        repository: ScriptListRepository
    ): SetScriptGeneralInfoUseCase = SetScriptGeneralInfoUseCase(repository)

    @Singleton
    @Provides
    fun provideSetUserUseCase(
        repository: ScriptListRepository
    ): SetUserUseCase = SetUserUseCase(repository)

    @Singleton
    @Provides
    fun provideInitScriptWorkerUseCase(
        repository: ScriptListRepository
    ): InitScriptWorkerUseCase = InitScriptWorkerUseCase(repository)
}