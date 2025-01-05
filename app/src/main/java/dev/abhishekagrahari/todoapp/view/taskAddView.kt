package dev.abhishekagrahari.todoapp.view


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun addTaskView(navController: NavController) {
       var firstField by remember { mutableStateOf("") }
       var secondField by remember { mutableStateOf("") }

       Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(16.dp),
           verticalArrangement = Arrangement.Top,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           // First text field
           OutlinedTextField(
               value = firstField,
               onValueChange = { firstField = it },
               label = { Text("Task Title") },
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(bottom = 16.dp),
               singleLine = true
           )

           // Second text field
           OutlinedTextField(
               value = secondField,
               onValueChange = { secondField = it },
               label = { Text("Task Description") },
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(bottom = 24.dp),
               singleLine = true
           )

           // Submit button
           Button(
               onClick = {
                   if (firstField.isNotBlank() && secondField.isNotBlank()) {
                       // pass a function to add the elements
                       firstField = "" // Clear the fields after submit
                       secondField = ""
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

