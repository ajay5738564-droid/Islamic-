package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.MainViewModel

@Composable
fun BookmarksDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val bookmarks by viewModel.allBookmarks.collectAsState()
    var selectedTypeFilter by remember { mutableStateOf<String?>(null) }

    val filtered = remember(bookmarks, selectedTypeFilter) {
        if (selectedTypeFilter == null) bookmarks else bookmarks.filter { it.type == selectedTypeFilter }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.testTag("bookmarks_dialog"),
        title = {
            Text(
                text = "Saved Bookmarks (${bookmarks.size})",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Filter Tabs
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    FilterChip(
                        selected = selectedTypeFilter == null,
                        onClick = { selectedTypeFilter = null },
                        label = { Text("All") }
                    )
                    FilterChip(
                        selected = selectedTypeFilter == "AYAH",
                        onClick = { selectedTypeFilter = "AYAH" },
                        label = { Text("Verses") }
                    )
                    FilterChip(
                        selected = selectedTypeFilter == "DUA",
                        onClick = { selectedTypeFilter = "DUA" },
                        label = { Text("Duas") }
                    )
                    FilterChip(
                        selected = selectedTypeFilter == "HADITH",
                        onClick = { selectedTypeFilter = "HADITH" },
                        label = { Text("Hadiths") }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                if (filtered.isEmpty()) {
                    Box(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                        Text(
                            text = "No saved bookmarks yet in this category.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    LazyColumn(modifier = Modifier.heightIn(max = 320.dp)) {
                        items(filtered, key = { it.id }) { item ->
                            ListItem(
                                headlineContent = { Text(item.title, fontWeight = FontWeight.Bold) },
                                supportingContent = { Text(item.subtitle, maxLines = 2) },
                                trailingContent = {
                                    IconButton(onClick = { viewModel.toggleBookmark(item) }) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Delete",
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    }
                                }
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}
