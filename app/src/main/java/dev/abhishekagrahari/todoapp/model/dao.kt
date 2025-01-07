package dev.abhishekagrahari.todoapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface dao {
    @Insert
    suspend fun insert(todo: todo)

    @Query("SELECT * FROM task_table")
    fun getAllUsers(): Flow<List<todo>>

    @Query("DELETE  FROM task_table where id=:id")
    suspend fun deleteSelectedTask(id: Int)
}
