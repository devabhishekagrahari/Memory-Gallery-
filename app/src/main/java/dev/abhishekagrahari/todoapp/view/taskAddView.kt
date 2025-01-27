package dev.abhishekagrahari.todoapp.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.abhishekagrahari.todoapp.viewModel.taskViewModel
import androidx.compose.material3.TextFieldDefaults

@Composable
fun addTaskView(
    navController: NavController,
    viewModel: taskViewModel
) {
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var selectedBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current // Retrieve the context here

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
            .background(Color(0xFFF8BBD0)) // Soft romantic pink background
    ) {
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

        // Romantic Header
        Text(
            text = "Save a Lovely Memory 💖",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            ),
            color = Color(0xFFD81B60),
            modifier = Modifier.padding(bottom = 24.dp),
        )

        // Title Input Field
        OutlinedTextField(
            value = taskTitle,
            onValueChange = { taskTitle = it },
            label = { Text("Title of Your Memory") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFFB61C58),
                unfocusedTextColor = Color(0xFFD81B60)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true
        )

        // Description Input Field
        OutlinedTextField(
            value = taskDescription,
            onValueChange = { taskDescription = it },
            label = { Text("Describe the Memory") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFFB61C58),
                unfocusedTextColor = Color(0xFFD81B60)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        // Pick Image Button
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                imagePickerLauncher.launch(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD81B60),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Pick an Image 🖼️", fontWeight = FontWeight.Bold)
        }

        // Display Selected Image
        selectedBitmap?.let {
            Text(
                text = "Selected Memory Image:",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                ),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Selected Memory Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .padding(bottom = 16.dp)
            )
        }

        // Submit Button
        Button(
            onClick = {
                if (taskTitle.isNotBlank() && taskDescription.isNotBlank() && selectedBitmap != null) {
                    viewModel.addUser(
                        taskName = taskTitle,
                        taskDescription = taskDescription,
                        image = selectedBitmap!!
                    )
                    taskTitle = "" // Clear fields after submit
                    taskDescription = ""
                    selectedBitmap = null
                }
                navController.navigate("home")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD81B60),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Memory 💌")
        }
    }
}
