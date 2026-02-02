package com.example.ahoy.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onChatClick: (ChatUser) -> Unit,
    onLogout: () -> Unit
) {
    // TEMP: will come from DashboardViewModel later
    val users = emptyList<ChatUser>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        TopAppBar(
            title = {
                Text(
                    text = "Ahoy",
                    fontWeight = FontWeight.Bold
                )
            },
            actions = {
                TextButton(onClick = onLogout) {
                    Text("Logout")
                }
            }
        )

        LazyColumn {
            items(users) { user ->
                ChatRow(
                    user = user,
                    onClick = { onChatClick(user) }
                )
            }
        }
    }
}

@Composable
private fun ChatRow(
    user: ChatUser,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0E0E0)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = user.name.first().uppercase(),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = user.name,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = user.lastMessage,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Text(
            text = user.time,
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

data class ChatUser(
    val uid: String,
    val name: String,
    val lastMessage: String,
    val time: String
)
