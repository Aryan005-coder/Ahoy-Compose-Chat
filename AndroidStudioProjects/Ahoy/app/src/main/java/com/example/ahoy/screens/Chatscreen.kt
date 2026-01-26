@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.ahoy.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ahoy.ViewModel.ChatViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    onNavigateBack: () -> Unit,
    viewModel: ChatViewModel = viewModel()
) {
    var input by remember { mutableStateOf("") }
    val messages by viewModel.messages.collectAsState()

    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.lastIndex)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            items(
                items = messages,
                key = { it.id }
            ) { msg ->
                Text(
                    text = msg.text,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }

        Row(modifier = Modifier.padding(8.dp)) {
            TextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Type a message") },
                singleLine = true
            )

            Button(
                onClick = {
                    viewModel.send(input.trim())
                    input = ""
                },
                enabled = input.isNotBlank()
            ) {
                Text("Send")
            }
        }
    }
}
