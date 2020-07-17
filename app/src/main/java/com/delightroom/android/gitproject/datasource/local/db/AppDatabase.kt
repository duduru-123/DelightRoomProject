package com.delightroom.android.gitproject.datasource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.delightroom.android.gitproject.datasource.local.dao.AppDataDao
import com.delightroom.android.gitproject.datasource.local.model.AppData


private const val DB_NAME = "gitproject.db"

@Database(entities = [AppData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDataDao(): AppDataDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}
