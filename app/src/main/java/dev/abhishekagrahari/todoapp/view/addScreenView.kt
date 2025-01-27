package dev.abhishekagrahari.todoapp.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.abhishekagrahari.todoapp.viewModel.taskViewModel

@Composable
fun addScreenView(navController: NavController, viewModel: taskViewModel) {
    BaseLayout(
        title = "Add Screen View",
        navController = navController
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            addTaskView(navController , viewModel)
        }

    }
}