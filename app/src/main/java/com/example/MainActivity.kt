package com.example

import com.example.ads.unity.UnityAdsManager
import com.example.ads.unity.UnityBannerAd
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ui.ExploreSubScreen
import com.example.ui.MainViewModel
import com.example.ui.NavigationTab
import com.example.ui.components.AudioMiniPlayerBar
import com.example.ui.components.DeenovaBottomNavigation
import com.example.ui.screens.BookmarksDialog
import com.example.ui.screens.ExitConfirmationDialog
import com.example.ui.screens.ExploreScreen
import com.example.ui.screens.GlobalSearchDialog
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LanguageSelectionDialog
import com.example.ui.screens.LocationPickerDialog
import com.example.ui.screens.PrayerScreen
import com.example.ui.screens.QuranScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UnityAdsManager.initialize(applicationContext)
        enableEdgeToEdge()
        setContent {
            val themeMode by viewModel.themeMode.collectAsState()
            val isDarkTheme = when (themeMode) {
                com.example.data.repository.ThemeMode.SYSTEM -> androidx.compose.foundation.isSystemInDarkTheme()
                com.example.data.repository.ThemeMode.LIGHT -> false
                com.example.data.repository.ThemeMode.DARK -> true
            }

            MyApplicationTheme(darkTheme = isDarkTheme) {
                OnlineOnlyGate {
                    DeenovaApp(viewModel = viewModel)
                }
            }
        }
    }
}


@Composable
private fun OnlineOnlyGate(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val connectivityManager = remember(context) {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    var isOnline by remember {
        mutableStateOf(hasValidatedInternet(connectivityManager))
    }

    DisposableEffect(connectivityManager) {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isOnline = hasValidatedInternet(connectivityManager)
            }

            override fun onLost(network: Network) {
                isOnline = hasValidatedInternet(connectivityManager)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                isOnline = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            }
        }
        connectivityManager.registerDefaultNetworkCallback(callback)
        onDispose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

    if (isOnline) {
        content()
    } else {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Internet connection required",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Please connect to the internet to use this app. Offline mode is not available.",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    isOnline = hasValidatedInternet(connectivityManager)
                }) {
                    Text("Retry")
                }
            }
        }
    }
}

private fun hasValidatedInternet(connectivityManager: ConnectivityManager): Boolean {
    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
        capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
}

@Composable
fun DeenovaApp(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val currentTab by viewModel.currentTab.collectAsState()
    val playingAyah by viewModel.currentPlayingAyah.collectAsState()
    val playbackState by viewModel.audioPlaybackState.collectAsState()
    val selectedSurah by viewModel.selectedSurah.collectAsState()
    val exploreSubScreen by viewModel.exploreSubScreen.collectAsState()
    val selectedKnowledgeArticle by viewModel.selectedKnowledgeArticle.collectAsState()
    val selectedNaatForDetails by viewModel.selectedNaatForDetails.collectAsState()
    val currentLanguage by viewModel.appLanguage.collectAsState()

    var showLocationPicker by remember { mutableStateOf(false) }
    var showGlobalSearch by remember { mutableStateOf(false) }
    var showBookmarks by remember { mutableStateOf(false) }
    var showLanguageDialog by remember { mutableStateOf(false) }
    var showExitDialog by remember { mutableStateOf(false) }
    var exploreReturnCount by remember { mutableIntStateOf(0) }

    // Step-by-step Back Press Handler
    BackHandler(enabled = true) {
        when {
            showExitDialog -> {
                showExitDialog = false
            }
            showLanguageDialog -> {
                showLanguageDialog = false
            }
            showLocationPicker -> {
                showLocationPicker = false
            }
            showGlobalSearch -> {
                showGlobalSearch = false
            }
            showBookmarks -> {
                showBookmarks = false
            }
            currentTab == NavigationTab.QURAN && selectedSurah != null -> {
                viewModel.clearSelectedSurah()
            }
            currentTab == NavigationTab.EXPLORE && selectedKnowledgeArticle != null -> {
                viewModel.selectKnowledgeArticle(null)
            }
            currentTab == NavigationTab.EXPLORE && selectedNaatForDetails != null -> {
                viewModel.selectNaatForDetails(null)
            }
            currentTab == NavigationTab.EXPLORE && exploreSubScreen != ExploreSubScreen.MAIN_MENU -> {
                viewModel.setExploreSubScreen(ExploreSubScreen.MAIN_MENU)
            }
            currentTab != NavigationTab.HOME -> {
                viewModel.setTab(NavigationTab.HOME)
            }
            else -> {
                // At root of HOME screen - show exit confirmation dialog instead of exiting in 1 click
                showExitDialog = true
            }
        }
    }

    val activity = context as? Activity

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            Column {
                // Keep the banner slot visible on every screen; Unity serves ads only when online inventory is available.
                UnityBannerAd(reloadKey = currentTab.ordinal)
                DeenovaBottomNavigation(
                    selectedTab = currentTab,
                    currentLanguage = currentLanguage,
                    onTabSelected = { tab ->
                        // Interstitials appear only occasionally at a natural Explore -> Home transition.
                        // Show an interstitial only occasionally when returning to Home.
                        // Never interrupt Quran or Prayer navigation.
                        if (
                            tab == NavigationTab.HOME &&
                            currentTab != NavigationTab.HOME &&
                            currentTab != NavigationTab.QURAN &&
                            currentTab != NavigationTab.PRAYER
                        ) {
                            exploreReturnCount += 1
                            if (exploreReturnCount % 5 == 0) {
                                activity?.let { UnityAdsManager.showInterstitial(it) }
                            }
                        }
                        viewModel.setTab(tab)
                    }
                )
            }
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
                        QuranScreen(
                            viewModel = viewModel
                        )
                    }
                    NavigationTab.PRAYER -> {
                        PrayerScreen(
                            viewModel = viewModel,
                            onOpenLocationPicker = { showLocationPicker = true }
                        )
                    }
                    NavigationTab.EXPLORE -> {
                        ExploreScreen(
                            viewModel = viewModel
                        )
                    }
                    NavigationTab.MORE -> {
                        SettingsScreen(
                            viewModel = viewModel,
                            onOpenLocationPicker = { showLocationPicker = true },
                            onOpenBookmarks = { showBookmarks = true },
                            onOpenLanguagePicker = { showLanguageDialog = true }
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
    if (showExitDialog) {
        ExitConfirmationDialog(
            viewModel = viewModel,
            onConfirmExit = {
                showExitDialog = false
                (context as? Activity)?.finish()
            },
            onDismiss = { showExitDialog = false }
        )
    }

    if (showLanguageDialog) {
        LanguageSelectionDialog(
            viewModel = viewModel,
            onDismiss = { showLanguageDialog = false }
        )
    }

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


