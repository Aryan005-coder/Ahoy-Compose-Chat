package com.example.ahoy.Navigation
sealed class Routes(val route: String) {
    object Dashboard : Routes("dashboard")
    object ChatScreen : Routes("chatscreen")
}