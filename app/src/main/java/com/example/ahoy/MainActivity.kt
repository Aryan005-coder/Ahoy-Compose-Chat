package com.example.ahoy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.ahoy.Navigation.AppNavigation
import com.example.ahoy.authentication.AuthViewModel
import com.example.ahoy.ui.theme.AhoyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AhoyTheme {

                val navController = rememberNavController()
                val authViewModel: AuthViewModel = viewModel()

                AppNavigation(
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
        }
    }
}
