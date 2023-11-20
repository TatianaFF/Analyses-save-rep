package com.example.myapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapp.models.Analysis
import com.example.myapp.models.OrganSystem
import com.example.myapp.models.Organ
import java.io.File

@Database(
    version = 1,
    entities = [
        OrganSystem::class,
        Organ::class,
        Analysis::class
    ]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): AppDao

    companion object {
        private const val DB_NAME = "health_database.db"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                        .createFromAsset("health_init.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}