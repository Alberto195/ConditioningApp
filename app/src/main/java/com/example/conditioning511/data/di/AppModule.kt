package com.example.conditioning511.data.di

import android.content.Context
import androidx.room.Room
import com.example.conditioning511.data.core.api_service.ScriptListApi
import com.example.conditioning511.data.core.repositories.ScriptListRepositoryImpl
import com.example.conditioning511.data.core.storage.UserScriptStorageDatabase
import com.example.conditioning511.data.core.storage.UserStorageSharedPreference
import com.example.conditioning511.data.core.storage.db.RoomDao
import com.example.conditioning511.data.core.storage.db.ScriptListRoomDatabase
import com.example.conditioning511.data.core.storage.db.UserScriptStorageDatabaseImpl
import com.example.conditioning511.data.core.storage.sharedpref.UserStorageSharedPrefImpl
import com.example.conditioning511.domain.core.repositories.ScriptListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//@Singleton
//@Component(modules = [AppModule::class, ContextModule::class, CorePresentationModule::class, RoomDaoDb::class])
//interface Component

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    companion object {
        private const val BASE_URL = "https://back.vc-app.ru/"
    }

    @Singleton
    @Provides
    fun provideScriptListApi(): ScriptListApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ScriptListApi::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
class ContextModule {
    @Provides
    fun provideContext(
        @ApplicationContext context: Context
    ): Context {
        return context
    }
}

@Module
@InstallIn(SingletonComponent::class)
class RoomDaoDb {
    @Singleton
    @Provides
    fun provideScriptListRoomDatabase(
        context: Context
    ): ScriptListRoomDatabase {
        return Room.databaseBuilder(
            context,
            ScriptListRoomDatabase::class.java,
            "database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideRoomDao(
        db: ScriptListRoomDatabase
    ): RoomDao {
        return db.getRoomDao()
    }

}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindUserScriptStorageDatabase(
        userScriptStorageDatabaseImpl: UserScriptStorageDatabaseImpl
    ): UserScriptStorageDatabase

    @Singleton
    @Binds
    fun bindUserStorageSharedPreference(
        userStorageSharedPrefImpl : UserStorageSharedPrefImpl
    ): UserStorageSharedPreference

    @Singleton
    @Binds
    fun bindScriptListRepository(
        scriptListRepositoryImpl: ScriptListRepositoryImpl,
    ): ScriptListRepository
}
