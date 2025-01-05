package dev.abhishekagrahari.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.abhishekagrahari.todoapp.ui.theme.TodoAppTheme
import dev.abhishekagrahari.todoapp.view.HomeScreenView


import dev.abhishekagrahari.todoapp.viewModel.SetupNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                  SetupNavGraph(navController = rememberNavController())
            }
        }
    }
}
