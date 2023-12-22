package com.example.firtuka2.data.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firtuka2.data.response.FramesItem


@Database(
    entities = [FramesItem::class, RemoteKeys::class],
    version = 7,
    exportSchema = false
)
abstract class GlassesDatabase : RoomDatabase() {
    abstract fun glassesDao(): GlassesDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    companion object {
        @Volatile
        private var INSTANCE: GlassesDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): GlassesDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    GlassesDatabase::class.java, "glasses_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}