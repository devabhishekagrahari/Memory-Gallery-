package dev.abhishekagrahari.todoapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dev.abhishekagrahari.todoapp.model.taskRepository
import dev.abhishekagrahari.todoapp.model.todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class taskViewModel(private val repository: taskRepository) : ViewModel() {
    val tasks : Flow<List<todo>> = repository.tasks

    fun addUser(taskName: String  , taskDescription : String ) {
        viewModelScope.launch {
            repository.addItem(todo(taskName = taskName , taskDescription = taskDescription))
        }
    }

    fun deleteSelectedTask(id :Int) {
        viewModelScope.launch {
            repository.deleteSelectedTask(id)
        }
    }
}
