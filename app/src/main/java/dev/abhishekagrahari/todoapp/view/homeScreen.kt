package dev.abhishekagrahari.todoapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.abhishekagrahari.todoapp.viewModel.SetupNavGraph
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenView(navController: NavController) {
    /*   val drawerState = rememberDrawerState(DrawerValue.Closed) // Drawer state
    val scope = rememberCoroutineScope() // Coroutine scope for toggling the drawer

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Content of the navigation drawer
            Spacer(modifier= Modifier.height(200.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                // Clickable Home Text
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.background(Color.White).padding(start = 15.dp, end = 15.dp, top = 20.dp)
                        .clickable {
                            // Handle Home click
                            println("Home clicked")
                        }
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Clickable Profile Text
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.background(Color.White).padding(start = 15.dp, end = 15.dp, top = 20.dp)
                        .clickable {
                            // Handle Profile click
                            println("Profile clicked")
                        }
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Clickable Settings Text
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.background(Color.White).padding(start = 15.dp, end = 15.dp, top = 20.dp)
                        .clickable {
                            // Handle Settings click
                            println("Settings clicked")
                        }
                )
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Top Bar") },
                        navigationIcon = {
                            IconButton(onClick = {
                                // Toggle the drawer when the hamburger icon is clicked
                                scope.launch { drawerState.open() }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Hamburger Menu",
                                    tint = Color.White
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Blue,
                            titleContentColor = Color.White
                        )
                    )
                },
                bottomBar = {
                    BottomAppBar(
                        containerColor = Color.Blue,  // Set background color for BottomAppBar
                        contentColor = Color.White,   // Set content color for BottomAppBar
                        contentPadding = PaddingValues(12.dp)
                    ) {
                        Text("Bottom Bar", color = Color.White, modifier = Modifier.padding(16.dp))
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { navController.navigate("addTask") }) {
                        Text("Add Task +")
                    }
                },
                content = { paddingValues ->
                    // Main content goes here
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        TaskListView(navController)
                    }

                }
            )
        }
    )
}
*/
    BaseLayout(
        title = "homeScreen",
        navController = navController
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            TaskListView(navController)
        }

    }
}
