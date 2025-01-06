package dev.abhishekagrahari.todoapp.view


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

           // Submit button
           Button(
               onClick = {
                   if (taskTitle.isNotBlank() && taskDescription.isNotBlank()) {
                       // pass a function to add the elements
                       viewModel.addUser(taskName= taskTitle , taskDescription = taskDescription)
                       taskTitle = "" // Clear the fields after submit
                       taskDescription = ""
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

