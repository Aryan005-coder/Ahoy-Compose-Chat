package com.example.ahoy.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.semantics.error
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ahoy.authentication.AuthScreen
import com.example.ahoy.authentication.AuthState
import com.example.ahoy.authentication.AuthViewModel

import com.example.ahoy.screens.ChatScreen
import com.example.ahoy.screens.DashboardScreen


@Composable
fun AppNavigation(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    val authState by authViewModel.authState.collectAsState(
        initial = AuthState.Loading
    )

    // 🔥 React to auth changes
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                navController.navigate(Routes.Dashboard.route) {
                    popUpTo(Routes.Auth.route) { inclusive = true }
                }
            }

            is AuthState.Unauthenticated -> {
                navController.navigate(Routes.Auth.route) {
                    popUpTo(0)
                }
            }

            AuthState.Loading -> Unit
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Auth.route // STATIC
    ) {

        composable(Routes.Auth.route) {
            val loading by authViewModel.loading.collectAsState()
            val error by authViewModel.error.collectAsState()

            AuthScreen(
                loading = loading,
                error = error,
                onLogin = { email, password ->
                    authViewModel.login(email, password)
                },
                onRegister = { email, password ->
                    authViewModel.register(email, password)
                }
            )
        }

        composable(Routes.Dashboard.route) {
            DashboardScreen(
                onChatClick = { user ->
                    navController.navigate(
                        Routes.ChatScreen.createRoute(
                            user.uid,
                            user.name
                        )
                    )
                },
                onLogout = {
                    authViewModel.logout()
                }
            )
        }

        composable(Routes.ChatScreen.route) {
            ChatScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
