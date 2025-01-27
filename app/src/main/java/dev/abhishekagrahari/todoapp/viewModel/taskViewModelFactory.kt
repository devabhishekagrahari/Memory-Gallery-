package dev.abhishekagrahari.todoapp.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.abhishekagrahari.todoapp.model.Myapplication

class taskViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(taskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return taskViewModel(Myapplication.provideItemRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}