package com.example.ahoy.Navigation

sealed class Routes(val route: String) {
    object Auth : Routes("auth")          // AuthScreen
    object Dashboard : Routes("dashboard")
    object ChatScreen : Routes("chat/{userId}/{userName}") {
        fun createRoute(userId: String, userName: String) =
            "chat/$userId/$userName"
    }
}
