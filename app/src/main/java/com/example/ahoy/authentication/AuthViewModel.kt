package com.example.ahoy.authentication

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    // ---- AUTH STATE ----
    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState

    // ---- UI STATE ----
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        checkAuth()
    }

    private fun checkAuth() {
        val user = auth.currentUser
        _authState.value =
            if (user == null) {
                AuthState.Unauthenticated
            } else {
                AuthState.Authenticated(user.uid) // ✅ userId included
            }
    }

    // ---- LOGIN ----
    fun login(email: String, password: String) {
        _loading.value = true
        _error.value = null

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                _authState.value =
                    AuthState.Authenticated(it.user!!.uid)
                _loading.value = false
            }
            .addOnFailureListener {
                _error.value = it.message
                _loading.value = false
            }
    }

    // ---- REGISTER ----
    fun register(email: String, password: String) {
        _loading.value = true
        _error.value = null

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                _authState.value =
                    AuthState.Authenticated(it.user!!.uid)
                _loading.value = false
            }
            .addOnFailureListener {
                _error.value = it.message
                _loading.value = false
            }
    }

    // ---- LOGOUT ----
    fun logout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }
}
