package dev.abhishekagrahari.todoapp.model

import android.content.Context
import androidx.room.Room

object Myapplication {
    private var INSTANCE: appDatabase? = null

    fun getDatabase(context: Context): appDatabase {
        if (INSTANCE == null) {
            synchronized(appDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    appDatabase::class.java,
                    "app_database"
                ).build()
            }
        }
        return INSTANCE!!
    }
    fun provideItemRepository(context: Context): taskRepository {
        val dao = getDatabase(context).userDao()
        return taskRepository(dao)
    }
}
