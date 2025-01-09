package dev.abhishekagrahari.todoapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseLayout(
    title: String,
    navController: androidx.navigation.NavController,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            if(drawerState.isOpen){
            // Drawer Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFEEFF1)) // Romantic pink background
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    "Menu",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFD81B60) // Romantic pink text color
                    ),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Divider(color = Color(0xFFD81B60))
                Spacer(modifier = Modifier.height(16.dp))

                listOf("Home" to "home", "Profile" to "profile", "Settings" to "settings").forEach { (label, route) ->
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFD81B60) // Romantic pink
                        ),
                        modifier = Modifier
                            .clickable {
                                navController.navigate(route)
                                scope.launch { drawerState.close() }
                            }
                            .padding(vertical = 12.dp)
                    )
                }
            }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                title,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = Color.White
                                )
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Open Drawer",
                                    tint = Color.White
                                )
                            }
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = Color(0xFFD81B60), // Romantic pink
                            titleContentColor = Color.White
                        )
                    )
                },
                bottomBar = {
                    BottomAppBar(
                        containerColor = Color(0xFFD81B60), // Romantic pink
                        contentColor = Color.White
                    ) {
                        Text(
                            text = "Made with ❤️",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                },
                content = content
            )
        }
    )
}
