package com.example.conditioning511.data.di

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.work.*
import com.example.conditioning511.data.core.api_service.ScriptListApi
import com.example.conditioning511.data.core.repositories.ScriptListRepositoryImpl
import com.example.conditioning511.data.core.storage.UserScriptStorageDatabase
import com.example.conditioning511.data.core.storage.UserStorageSharedPreference
import com.example.conditioning511.data.core.storage.db.RoomDao
import com.example.conditioning511.data.core.storage.db.ScriptListRoomDatabase
import com.example.conditioning511.data.core.storage.db.UserScriptStorageDatabaseImpl
import com.example.conditioning511.data.core.storage.sharedpref.UserStorageSharedPrefImpl
import com.example.conditioning511.data.core.workers.ScriptWorker
import com.example.conditioning511.domain.core.repositories.ScriptListRepository
import com.example.conditioning511.presentation.core.di.CorePresentationModule
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ContextModule::class, CorePresentationModule::class])
interface Component

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://back.vc-app.ru/"

    @Singleton
    @Provides
    fun provideScriptListApi(): ScriptListApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ScriptListApi::class.java)
    }

    @Singleton
    @Binds
    fun bindUserScriptStorageDatabase(
        roomDao: RoomDao
    ): UserScriptStorageDatabase = UserScriptStorageDatabaseImpl(roomDao = roomDao)

    @Singleton
    @Binds
    fun bindUserStorageSharedPreference(
        context: Context
    ): UserStorageSharedPreference = UserStorageSharedPrefImpl(context = context)

    @Singleton
    @Provides
    fun provideRoomDao(
        context: Context
    ): RoomDao {
        return ScriptListRoomDatabase.getInstance(context).getRoomDao()
    }

    @Singleton
    @Binds
    fun bindScriptListRepository(
        api: ScriptListApi,
        userScriptStorageDatabase: UserScriptStorageDatabase,
        userStorageSharedPreference: UserStorageSharedPreference,
        context: Context
    ): ScriptListRepository = ScriptListRepositoryImpl(
        api = api,
        userScriptStorageDatabase = userScriptStorageDatabase,
        userStorageSharedPreference = userStorageSharedPreference,
        context = context
    )

}

@Module
class ContextModule(context: Context) {
    private val mContext: Context = context

    @Provides
    fun provideContext(): Context {
        return mContext
    }

}