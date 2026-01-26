package com.example.ahoy.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ahoy.data.ChatMessage
import com.example.ahoy.data.repository.ChatRepository


class ChatViewModel : ViewModel() {

    private val repository = ChatRepository()

    private val currentUserId = "TEMP_USER_1"
    private val receiverId = "TEMP_USER_2"
    private val chatId = "TEMP_CHAT"

    val messages = repository.messages

    fun send(text: String) {
        if (text.isBlank()) return

        val message = ChatMessage(
            id = System.currentTimeMillis().toString(),
            senderId = currentUserId,
            receiverId = receiverId,
            text = text
        )

        repository.sendMessage(chatId, message)
    }
}
