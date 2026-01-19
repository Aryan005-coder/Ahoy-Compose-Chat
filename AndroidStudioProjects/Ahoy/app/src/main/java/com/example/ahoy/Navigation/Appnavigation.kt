package com.example.ahoy.navigation   // <-- also fixed lowercase

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ahoy.Navigation.Routes
import com.example.ahoy.screens.ChatScreen
import com.example.ahoy.screens.DashboardScreen
import com.example.ahoy.screens.ChatUser

@Composable
fun AppNavigation(navController: NavHostController) {

    // TEMP fake data (replace later with ViewModel / Firebase)
    val users = listOf(
        ChatUser("1", "Aryan", "Hey!", "10:30 AM"),
        ChatUser("2", "Rohit", "What’s up?", "Yesterday")
    )

    NavHost(
        navController = navController,
        startDestination = Routes.Dashboard.route
    ) {

        composable(Routes.Dashboard.route) {
            DashboardScreen(
                users = users,
                onChatClick = {
                    navController.navigate(Routes.ChatScreen.route)
                }
            )
        }

        composable(Routes.ChatScreen.route) {
            ChatScreen(
                userName = "Aryan", // temporary
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
