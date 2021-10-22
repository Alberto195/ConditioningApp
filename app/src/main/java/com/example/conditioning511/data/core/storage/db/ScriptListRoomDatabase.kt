package com.example.conditioning511.data.core.storage.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.conditioning511.data.core.storage.db.models.ScriptDetailsDbModel
import com.example.conditioning511.data.core.storage.db.models.ScriptGeneralInfoDbModel

@Database(entities = [ScriptGeneralInfoDbModel::class, ScriptDetailsDbModel::class], version = 1, exportSchema = false)
abstract class ScriptListRoomDatabase : RoomDatabase() {

    abstract fun getRoomDao(): RoomDao

    companion object {

        @Volatile
        private var database: ScriptListRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ScriptListRoomDatabase {
            return if (database == null) {
                database = Room
                    .databaseBuilder(
                        context,
                        ScriptListRoomDatabase::class.java,
                        "database"
                    )
                    .fallbackToDestructiveMigration().build()

                database as ScriptListRoomDatabase
            } else database as ScriptListRoomDatabase
        }
    }
}
