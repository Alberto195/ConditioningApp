package com.example.conditioning511.presentation.core.di

import com.example.conditioning511.domain.core.repositories.RoomDbRepository
import com.example.conditioning511.domain.core.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CorePresentationModule {

    @Singleton
    @Provides
    fun provideGetScriptDetailsUseCase(
        repository: RoomDbRepository
    ): GetScriptDetailsUseCase = GetScriptDetailsUseCase(repository)

    @Singleton
    @Provides
    fun provideGetScriptGeneralInfoIseCase(
        repository: RoomDbRepository
    ): GetScriptGeneralInfoIseCase = GetScriptGeneralInfoIseCase(repository)

    @Singleton
    @Provides
    fun provideGetUserUseCase(
        repository: RoomDbRepository
    ): GetUserUseCase = GetUserUseCase(repository)

    @Singleton
    @Provides
    fun provideInsertDetailedScriptUseCase(
        repository: RoomDbRepository
    ): InsertDetailedScriptUseCase = InsertDetailedScriptUseCase(repository)

    @Singleton
    @Provides
    fun provideSetScriptGeneralInfoUseCase(
        repository: RoomDbRepository
    ): SetScriptGeneralInfoUseCase = SetScriptGeneralInfoUseCase(repository)

    @Singleton
    @Provides
    fun provideSetUserUseCase(
        repository: RoomDbRepository
    ): SetUserUseCase = SetUserUseCase(repository)

    @Singleton
    @Provides
    fun provideInitScriptWorkerUseCase(
        repository: RoomDbRepository
    ): InitScriptWorkerUseCase = InitScriptWorkerUseCase(repository)
}