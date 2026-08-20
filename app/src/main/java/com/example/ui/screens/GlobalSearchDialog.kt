package com.example.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.repository.DuaRepository
import com.example.data.repository.HadithRepository
import com.example.data.repository.QuranRepository
import com.example.ui.ExploreSubScreen
import com.example.ui.MainViewModel
import com.example.ui.NavigationTab

@Composable
fun GlobalSearchDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val matchedSurahs = remember(searchQuery) {
        if (searchQuery.length < 2) emptyList()
        else QuranRepository.ALL_114_SURAHS.filter {
            it.nameEnglish.contains(searchQuery, ignoreCase = true) ||
                    it.englishMeaning.contains(searchQuery, ignoreCase = true)
        }.take(3)
    }

    val matchedDuas = remember(searchQuery) {
        if (searchQuery.length < 2) emptyList()
        else DuaRepository.ALL_DUAS.filter {
            it.titleEnglish.contains(searchQuery, ignoreCase = true) ||
                    it.englishTranslation.contains(searchQuery, ignoreCase = true)
        }.take(3)
    }

    val matchedHadiths = remember(searchQuery) {
        if (searchQuery.length < 2) emptyList()
        else HadithRepository.ALL_HADITHS.filter {
            it.topicEnglish.contains(searchQuery, ignoreCase = true) ||
                    it.englishTranslation.contains(searchQuery, ignoreCase = true)
        }.take(3)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.testTag("global_search_dialog"),
        title = {
            Text(
                text = "Search Deenova",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth().testTag("global_search_field"),
                    placeholder = { Text("Search Quran, Duas, Hadiths...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Close, contentDescription = null)
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                if (searchQuery.length < 2) {
                    Text(
                        text = "Type at least 2 characters to search across all Islamic modules.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    LazyColumn(modifier = Modifier.heightIn(max = 320.dp)) {
                        if (matchedSurahs.isNotEmpty()) {
                            item {
                                Text("Quran Surahs", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            }
                            items(matchedSurahs) { surah ->
                                ListItem(
                                    headlineContent = { Text("${surah.number}. ${surah.nameEnglish}") },
                                    supportingContent = { Text(surah.englishMeaning) },
                                    modifier = Modifier.clickable {
                                        viewModel.selectSurah(surah)
                                        viewModel.setTab(NavigationTab.QURAN)
                                        onDismiss()
                                    }
                                )
                            }
                        }

                        if (matchedDuas.isNotEmpty()) {
                            item {
                                Text("Duas & Azkar", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            }
                            items(matchedDuas) { dua ->
                                ListItem(
                                    headlineContent = { Text(dua.titleEnglish) },
                                    supportingContent = { Text(dua.englishTranslation, maxLines = 1) },
                                    modifier = Modifier.clickable {
                                        viewModel.setTab(NavigationTab.EXPLORE)
                                        viewModel.setExploreSubScreen(ExploreSubScreen.DUA_LIST)
                                        onDismiss()
                                    }
                                )
                            }
                        }

                        if (matchedHadiths.isNotEmpty()) {
                            item {
                                Text("Hadiths", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            }
                            items(matchedHadiths) { hadith ->
                                ListItem(
                                    headlineContent = { Text(hadith.topicEnglish) },
                                    supportingContent = { Text(hadith.reference) },
                                    modifier = Modifier.clickable {
                                        viewModel.setTab(NavigationTab.EXPLORE)
                                        viewModel.setExploreSubScreen(ExploreSubScreen.HADITH_LIST)
                                        onDismiss()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Close") }
        }
    )
}
