package com.example.ahoy.data.repository

import com.example.ahoy.data.ChatMessage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatRepository {

    private val firestore = FirebaseFirestore.getInstance()

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private var listener: ListenerRegistration? = null

    /** Start listening to messages */
    fun startListening(chatId: String) {
        stopListening() // safety: avoid duplicate listeners

        listener = firestore
            .collection("chats")
            .document(chatId)
            .collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, error ->
                if (error != null || snapshot == null) return@addSnapshotListener

                _messages.value = snapshot.documents
                    .mapNotNull { it.toObject(ChatMessage::class.java) }
            }
    }

    /** Send a message */
    fun sendMessage(chatId: String, message: ChatMessage) {
        firestore
            .collection("chats")
            .document(chatId)
            .collection("messages")
            .add(message)
    }

    /** Stop listening to messages */
    fun stopListening() {
        listener?.remove()
        listener = null
    }
}
