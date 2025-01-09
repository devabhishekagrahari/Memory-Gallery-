package dev.abhishekagrahari.todoapp.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.abhishekagrahari.todoapp.viewModel.taskViewModel

@Composable
fun HomeScreenView(navController: NavController , viewModel: taskViewModel) {
    BaseLayout(
        title = "Memory Gallery",
        navController = navController
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            TaskListView(navController , viewModel)
        }

    }
}
