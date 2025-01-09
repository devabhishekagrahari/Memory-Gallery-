package dev.abhishekagrahari.todoapp.viewModel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dev.abhishekagrahari.todoapp.model.taskRepository
import dev.abhishekagrahari.todoapp.model.todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class taskViewModel(private val repository: taskRepository) : ViewModel() {
    val tasks : Flow<List<todo>> = repository.tasks

    fun addUser(taskName: String  , taskDescription : String , image: Bitmap) {
        viewModelScope.launch {
            repository.addItem(todo(taskName = taskName , taskDescription = taskDescription ,
                image= bitmapToByteArray(image)))
        }
    }

    fun deleteSelectedTask(id :Int) {
        viewModelScope.launch {
            repository.deleteSelectedTask(id)
        }
    }


    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}
