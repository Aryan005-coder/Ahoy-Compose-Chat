package com.example.ahoy.ViewModel

import androidx.lifecycle.ViewModel
import com.example.ahoy.data.UserRepository

class UserViewModel : ViewModel() {

    private val repository = UserRepository()
    val user = repository.currentUser

    init {
        repository.observeCurrentUser()
    }

    fun setOnline(isOnline: Boolean) {
        repository.setOnlineStatus(isOnline)
    }

    override fun onCleared() {
        repository.stopObservingCurrentUser()
        super.onCleared()
    }
}