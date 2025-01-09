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
                image= degradeBitmapToUnderSize(image)))
        }
    }

    fun deleteSelectedTask(id :Int) {
        viewModelScope.launch {
            repository.deleteSelectedTask(id)
        }
    }
    fun degradeBitmapToUnderSize(bitmap: Bitmap, targetSizeInBytes: Int = 1_500_000): ByteArray {
        var compressionQuality = 100
        var resizedBitmap = bitmap
        val baos = ByteArrayOutputStream()

        // Step 1: Resize the Bitmap (if needed)
        if (bitmap.byteCount > targetSizeInBytes) {
            val width = bitmap.width
            val height = bitmap.height
            val scalingFactor = Math.sqrt(targetSizeInBytes.toDouble() / bitmap.byteCount).toFloat()
            resizedBitmap = Bitmap.createScaledBitmap(
                bitmap,
                (width * scalingFactor).toInt(),
                (height * scalingFactor).toInt(),
                true
            )
        }

        // Step 2: Compress the Bitmap
        do {
            baos.reset()
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, compressionQuality, baos)
            compressionQuality -= 5 // Reduce quality by 5% in each iteration
        } while (baos.size() > targetSizeInBytes && compressionQuality > 10)

        return baos.toByteArray()
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
