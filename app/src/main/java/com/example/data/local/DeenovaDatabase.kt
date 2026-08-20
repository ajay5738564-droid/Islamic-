package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        BookmarkEntity::class,
        TasbeehHistoryEntity::class,
        OfflineAyahEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DeenovaDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun tasbeehDao(): TasbeehDao
    abstract fun offlineAyahDao(): OfflineAyahDao

    companion object {
        @Volatile
        private var INSTANCE: DeenovaDatabase? = null

        fun getDatabase(context: Context): DeenovaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DeenovaDatabase::class.java,
                    "deenova_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
