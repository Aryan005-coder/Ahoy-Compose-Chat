package com.example.ahoy.authentication

sealed class AuthState {
    object Loading : AuthState()
    object Unauthenticated : AuthState()
    data class Authenticated(val userId: String) : AuthState()
}
