package com.example.ahoy.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ahoy.data.User
import com.example.ahoy.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    init {
        observeUsers()
    }

    private fun observeUsers() {
        viewModelScope.launch {
            userRepository.observeUsers().collect { userList ->
                _users.value = userList
            }
        }
    }
}
