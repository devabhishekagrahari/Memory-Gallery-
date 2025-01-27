package dev.abhishekagrahari.todoapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.abhishekagrahari.todoapp.model.todo
import dev.abhishekagrahari.todoapp.viewModel.taskViewModel

@Composable
fun TaskListView(navController: NavController, taskViewModel: taskViewModel) {
    val tasks by taskViewModel.tasks.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top= 3.dp)
            .padding(3.dp)
            .background(Color(0xFFF8BBD0)) // Light pink color for a soft romantic background

    ) {
        Button(
            onClick = { navController.navigate("addScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD81B60), // Romantic pink
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Save a Memory", fontWeight = FontWeight.Bold)
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { task ->
                TaskCard(task = task, taskViewModel = taskViewModel)
            }
        }
    }
}

@Composable
fun TaskCard(task: todo, taskViewModel: taskViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer, // Soft romantic background
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(16.dp) // Rounded edges for a softer look
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val bitmap = taskViewModel.byteArrayToBitmap(task.image) // Convert ByteArray to Bitmap
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Memory of ${task.taskName}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Memory ID: ${task.id}",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )

            Text(
                text = task.taskName,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD81B60) // Romantic pink
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )

            Text(
                text = task.taskDescription,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = { taskViewModel.deleteSelectedTask(task.id) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD81B60), // Romantic pink
                    contentColor = Color.White
                ),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Forget")
            }
        }
    }
}
