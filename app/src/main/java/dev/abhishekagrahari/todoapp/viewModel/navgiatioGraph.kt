package dev.abhishekagrahari.todoapp.viewModel


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.abhishekagrahari.todoapp.view.HomeScreenView
import dev.abhishekagrahari.todoapp.view.TaskListView
import dev.abhishekagrahari.todoapp.view.addScreenView
import dev.abhishekagrahari.todoapp.view.addTaskView


@Composable
fun SetupNavGraph(navController: NavHostController , viewModel: taskViewModel) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home"){
            HomeScreenView(navController , viewModel)
        }
        composable("addScreen"){
            addScreenView(navController , viewModel)
        }
        composable("taskView") {
            TaskListView(navController , viewModel)
        }
        composable("addTask") {
            addTaskView(navController , viewModel)
        }
    }
}
