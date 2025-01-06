package dev.abhishekagrahari.todoapp.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [todo::class], version = 1 , exportSchema = false)
abstract class appDatabase : RoomDatabase() {
    abstract fun userDao(): dao
}
