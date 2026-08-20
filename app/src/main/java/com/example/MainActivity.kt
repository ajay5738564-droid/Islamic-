package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.MainViewModel
import com.example.ui.NavigationTab
import com.example.ui.components.AudioMiniPlayerBar
import com.example.ui.components.DeenovaBottomNavigation
import com.example.ui.screens.BookmarksDialog
import com.example.ui.screens.ExploreScreen
import com.example.ui.screens.GlobalSearchDialog
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LocationPickerDialog
import com.example.ui.screens.PrayerScreen
import com.example.ui.screens.QuranScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                DeenovaApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun DeenovaApp(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val currentTab by viewModel.currentTab.collectAsState()
    val playingAyah by viewModel.currentPlayingAyah.collectAsState()
    val playbackState by viewModel.audioPlaybackState.collectAsState()
    val selectedSurah by viewModel.selectedSurah.collectAsState()

    var showLocationPicker by remember { mutableStateOf(false) }
    var showGlobalSearch by remember { mutableStateOf(false) }
    var showBookmarks by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            DeenovaBottomNavigation(
                selectedTab = currentTab,
                onTabSelected = { tab -> viewModel.setTab(tab) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Main Screen Content
            when (currentTab) {
                NavigationTab.HOME -> {
                    HomeScreen(
                        viewModel = viewModel,
                        onOpenLocationPicker = { showLocationPicker = true },
                        onOpenGlobalSearch = { showGlobalSearch = true },
                        onOpenBookmarks = { showBookmarks = true }
                    )
                }
                NavigationTab.QURAN -> {
                    QuranScreen(viewModel = viewModel)
                }
                NavigationTab.PRAYER -> {
                    PrayerScreen(
                        viewModel = viewModel,
                        onOpenLocationPicker = { showLocationPicker = true }
                    )
                }
                NavigationTab.EXPLORE -> {
                    ExploreScreen(viewModel = viewModel)
                }
                NavigationTab.MORE -> {
                    SettingsScreen(
                        viewModel = viewModel,
                        onOpenLocationPicker = { showLocationPicker = true },
                        onOpenBookmarks = { showBookmarks = true }
                    )
                }
            }

            // Floating Mini Audio Player when recitation is active
            if (playingAyah != null) {
                AudioMiniPlayerBar(
                    currentAyah = playingAyah,
                    surahName = selectedSurah?.nameEnglish ?: "Surah Al-Fatihah",
                    playbackState = playbackState,
                    onPlayPauseClick = {
                        if (playingAyah != null) {
                            viewModel.playAyahAudio(playingAyah!!)
                        }
                    },
                    onCloseClick = { viewModel.stopAudio() },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 12.dp)
                )
            }
        }
    }

    // Modal Dialogs
    if (showLocationPicker) {
        LocationPickerDialog(
            viewModel = viewModel,
            onDismiss = { showLocationPicker = false }
        )
    }

    if (showGlobalSearch) {
        GlobalSearchDialog(
            viewModel = viewModel,
            onDismiss = { showGlobalSearch = false }
        )
    }

    if (showBookmarks) {
        BookmarksDialog(
            viewModel = viewModel,
            onDismiss = { showBookmarks = false }
        )
    }
}
