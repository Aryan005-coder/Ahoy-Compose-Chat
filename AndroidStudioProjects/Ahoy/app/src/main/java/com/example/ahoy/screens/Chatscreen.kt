@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.ahoy.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// This OptIn is needed for experimental Material 3 APIs like TopAppBar and TextField
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    userName: String,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            // This is the "band above" you mentioned
            ChatScreenTopBar(
                userName = userName,
                onBackClick = onNavigateBack
            )
        },
        bottomBar = {
            // This is the message writing pad at the bottom
            MessageInputSection()
        }
    ) { innerPadding ->
        // This is where the chat messages will appear
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF0F4F8))
        ) {

        }
    }
}

@Composable
fun ChatScreenTopBar(userName: String, onBackClick: () -> Unit) {

    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = userName.firstOrNull()?.uppercase() ?: "U",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                // The user's name fetched from authentication
                Text(
                    text = userName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        },
        navigationIcon = {
            // Back button to navigate to the previous screen
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface, // Clean white background
            titleContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Composable
fun MessageInputSection() {
    // State to hold the text the user is typing
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        // This is the message writing pad you wanted
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // The EditText is called TextField in Jetpack Compose
            TextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message...") },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            // A send button
            IconButton(onClick = { /* TODO: Implement send message logic */ }) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send Message",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}


// A preview to see how your UI looks in Android Studio
@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        userName = "Aryan Singh", // Example name for the preview
        onNavigateBack = {}
    )
}
