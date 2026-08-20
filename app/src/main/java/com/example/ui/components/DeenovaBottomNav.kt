package com.example.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mosque
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Mosque
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.repository.AppLanguage
import com.example.ui.NavigationTab
import com.example.ui.util.AppStrings

@Composable
fun DeenovaBottomNavigation(
    selectedTab: NavigationTab,
    onTabSelected: (NavigationTab) -> Unit,
    modifier: Modifier = Modifier,
    currentLanguage: AppLanguage = AppLanguage.URDU
) {
    NavigationBar(
        modifier = modifier.testTag("deenova_bottom_nav"),
        tonalElevation = 8.dp,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        NavigationBarItem(
            selected = selectedTab == NavigationTab.HOME,
            onClick = { onTabSelected(NavigationTab.HOME) },
            icon = {
                Icon(
                    imageVector = if (selectedTab == NavigationTab.HOME) Icons.Filled.Home else Icons.Outlined.Home,
                    contentDescription = AppStrings.tabHome(currentLanguage)
                )
            },
            label = { Text(AppStrings.tabHome(currentLanguage)) },
            modifier = Modifier.testTag("nav_item_home")
        )

        NavigationBarItem(
            selected = selectedTab == NavigationTab.QURAN,
            onClick = { onTabSelected(NavigationTab.QURAN) },
            icon = {
                Icon(
                    imageVector = if (selectedTab == NavigationTab.QURAN) Icons.Filled.AutoStories else Icons.Outlined.AutoStories,
                    contentDescription = AppStrings.tabQuran(currentLanguage)
                )
            },
            label = { Text(AppStrings.tabQuran(currentLanguage)) },
            modifier = Modifier.testTag("nav_item_quran")
        )

        NavigationBarItem(
            selected = selectedTab == NavigationTab.PRAYER,
            onClick = { onTabSelected(NavigationTab.PRAYER) },
            icon = {
                Icon(
                    imageVector = if (selectedTab == NavigationTab.PRAYER) Icons.Filled.Mosque else Icons.Outlined.Mosque,
                    contentDescription = AppStrings.tabPrayer(currentLanguage)
                )
            },
            label = { Text(AppStrings.tabPrayer(currentLanguage)) },
            modifier = Modifier.testTag("nav_item_prayer")
        )

        NavigationBarItem(
            selected = selectedTab == NavigationTab.EXPLORE,
            onClick = { onTabSelected(NavigationTab.EXPLORE) },
            icon = {
                Icon(
                    imageVector = if (selectedTab == NavigationTab.EXPLORE) Icons.Filled.Explore else Icons.Outlined.Explore,
                    contentDescription = AppStrings.tabExplore(currentLanguage)
                )
            },
            label = { Text(AppStrings.tabExplore(currentLanguage)) },
            modifier = Modifier.testTag("nav_item_explore")
        )

        NavigationBarItem(
            selected = selectedTab == NavigationTab.MORE,
            onClick = { onTabSelected(NavigationTab.MORE) },
            icon = {
                Icon(
                    imageVector = if (selectedTab == NavigationTab.MORE) Icons.Filled.Menu else Icons.Outlined.Menu,
                    contentDescription = AppStrings.tabSettings(currentLanguage)
                )
            },
            label = { Text(AppStrings.tabSettings(currentLanguage)) },
            modifier = Modifier.testTag("nav_item_more")
        )
    }
}

