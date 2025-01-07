package dev.abhishekagrahari.todoapp.model

import kotlinx.coroutines.flow.Flow

class taskRepository(private val dao: dao) {

    val tasks: Flow<List<todo>> = dao.getAllUsers()

    suspend fun addItem(todo: todo) = dao.insert(todo)
    suspend fun deleteSelectedTask(id: Int)= dao.deleteSelectedTask(id)
}