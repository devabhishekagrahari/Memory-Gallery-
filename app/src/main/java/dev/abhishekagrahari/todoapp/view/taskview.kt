package dev.abhishekagrahari.todoapp.view

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Data class for Task
data class Task(val title: String, val description: String)

// Function to create dummy tasks
fun createNewTask(): List<Task> {
    return listOf(
        Task("Buy groceries", "Milk, Eggs, Bread, Butter"),
        Task("Morning Workout", "Complete 30 mins of cardio"),
        Task("Complete Homework", "Finish math and science assignments"),
        Task("Prepare Presentation", "Work on slides for Monday's meeting"),
        Task("Read a Book", "Complete 2 chapters of 'Atomic Habits'"),
    )
}

@Composable
fun TaskListView(navController: NavController) {
    // Remember a list of tasks
    val tasks = remember { createNewTask() }

 // Properly using padding values here
            Column(modifier = Modifier.padding(16.dp)) {
                Button(onClick = { navController.navigate("addTask") }) {
                    Text("Add Task")
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp), // Adjust padding for the list
                    verticalArrangement = Arrangement.spacedBy(8.dp) // Space between items
                ) {
                    items(tasks) { task ->
                        TaskCard(task = task)
                    }
                }
            }
        }

@Composable
fun TaskCard(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp), // Padding on left and right
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}


