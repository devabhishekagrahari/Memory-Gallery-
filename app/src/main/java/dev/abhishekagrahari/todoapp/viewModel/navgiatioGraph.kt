package dev.abhishekagrahari.todoapp.viewModel

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.abhishekagrahari.todoapp.view.HomeScreenView
import dev.abhishekagrahari.todoapp.view.TaskListView
import dev.abhishekagrahari.todoapp.view.addTaskView

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home"){
            HomeScreenView(navController)
        }
        composable("taskView") {
            TaskListView(navController)
        }
        composable("addTask") {
            addTaskView(navController)
        }
    }
}
