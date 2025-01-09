package dev.abhishekagrahari.todoapp.view


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavController
import dev.abhishekagrahari.todoapp.model.todo
import dev.abhishekagrahari.todoapp.viewModel.taskViewModel

@Composable
fun addTaskView(navController: NavController,
                viewModel: taskViewModel
) {
       var taskTitle by remember { mutableStateOf("") }
       var taskDescription by remember { mutableStateOf("") }
    var selectedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current // Retrieve the context here

    // Image picker launcher
    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri: Uri? = result.data?.data
            uri?.let {
                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                selectedBitmap = bitmap
            }
        }
    }



       Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(16.dp),
           verticalArrangement = Arrangement.Top,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {

           // First text field
           OutlinedTextField(
               value = taskTitle,
               onValueChange = { taskTitle = it },
               label = { Text("Task Title") },
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(bottom = 16.dp),
               singleLine = true
           )

           // Second text field
           OutlinedTextField(
               value = taskDescription,
               onValueChange = { taskDescription = it },
               label = { Text("Task Description") },
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(bottom = 24.dp),
               singleLine = true
           )
           Button(
               onClick = {
                   val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                   imagePickerLauncher.launch(intent)
               },
               modifier = Modifier.fillMaxWidth()
           ) {
               Text("Pick Image")
           }

           selectedBitmap?.let {
               Text("Selected Image:")
               Spacer(modifier = Modifier.height(8.dp))
               Image(
                   bitmap = it.asImageBitmap(),
                   contentDescription = "Selected Image",
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(200.dp)
               )
           }

           // Submit button
           Button(
               onClick = {
                   if (taskTitle.isNotBlank() && taskDescription.isNotBlank() && selectedBitmap != null) {
                       // pass a function to add the elements
                       viewModel.addUser(taskName= taskTitle ,
                           taskDescription = taskDescription ,
                           image= selectedBitmap!!)
                       taskTitle = "" // Clear the fields after submit
                       taskDescription = ""
                       selectedBitmap = null
                   }
                   navController.navigate("home")
               },
               modifier = Modifier.fillMaxWidth(),
               colors = ButtonDefaults.buttonColors(
                   containerColor = MaterialTheme.colorScheme.primary,
                   contentColor = MaterialTheme.colorScheme.onPrimary
               )
           ) {
               Text("Submit")
           }
       }
   }

