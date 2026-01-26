package com.example.ahoy.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow

class UserRepository {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private var currentUserListener: ListenerRegistration? = null

    fun observeCurrentUser() {
        val uid = auth.currentUser?.uid ?: return

        currentUserListener?.remove()
        currentUserListener = firestore
            .collection("users")
            .document(uid)
            .addSnapshotListener { snapshot, _ ->
                _currentUser.value = snapshot?.toObject(User::class.java)
            }
    }

    fun stopObservingCurrentUser() {
        currentUserListener?.remove()
        currentUserListener = null
    }

    fun setOnlineStatus(isOnline: Boolean) {
        val uid = auth.currentUser?.uid ?: return

        firestore.collection("users")
            .document(uid)
            .update(
                "isOnline", isOnline,
                "lastSeen", System.currentTimeMillis()
            )
    }
    fun observeUsers(): Flow<List<User>> = callbackFlow {
        val listener = firestore.collection("users")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val users = snapshot?.documents
                    ?.mapNotNull { it.toObject(User::class.java) }
                    .orEmpty()

                trySend(users)
            }

        awaitClose { listener.remove() }
    }

}
