package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.repository.AppLanguage
import com.example.ui.MainViewModel
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.util.AppStrings

@Composable
fun LanguageSelectionDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val currentLanguage by viewModel.appLanguage.collectAsState()

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag("language_selection_dialog"),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(EmeraldPrimary.copy(alpha = 0.12f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = "Language",
                            tint = EmeraldPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Column {
                        Text(
                            text = AppStrings.selectLanguageTitle(currentLanguage),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "اردو • हिन्दी • English",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Language Option: URDU
                LanguageOptionCard(
                    title = "اردو (Urdu)",
                    subtitle = "مکمل اردو ترجمہ، قرآنی اعراب اور تفاسیر",
                    flag = "🇵🇰",
                    isSelected = currentLanguage == AppLanguage.URDU,
                    testTag = "lang_opt_urdu",
                    onClick = {
                        viewModel.setLanguage(AppLanguage.URDU)
                        viewModel.triggerHapticFeedback()
                        onDismiss()
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Language Option: HINDI
                LanguageOptionCard(
                    title = "हिन्दी (Hindi)",
                    subtitle = "संपूर्ण इस्लामी जानकारी, दुआएं व नमाज़ गाइड",
                    flag = "🇮🇳",
                    isSelected = currentLanguage == AppLanguage.HINDI,
                    testTag = "lang_opt_hindi",
                    onClick = {
                        viewModel.setLanguage(AppLanguage.HINDI)
                        viewModel.triggerHapticFeedback()
                        onDismiss()
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Language Option: ENGLISH
                LanguageOptionCard(
                    title = "English",
                    subtitle = "Full English translation, transliteration & guides",
                    flag = "🇬🇧",
                    isSelected = currentLanguage == AppLanguage.ENGLISH,
                    testTag = "lang_opt_english",
                    onClick = {
                        viewModel.setLanguage(AppLanguage.ENGLISH)
                        viewModel.triggerHapticFeedback()
                        onDismiss()
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .align(Alignment.End)
                        .testTag("close_language_dialog")
                ) {
                    Text(
                        text = AppStrings.back(currentLanguage),
                        fontWeight = FontWeight.Bold,
                        color = EmeraldPrimary
                    )
                }
            }
        }
    }
}

@Composable
private fun LanguageOptionCard(
    title: String,
    subtitle: String,
    flag: String,
    isSelected: Boolean,
    testTag: String,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) EmeraldPrimary.copy(alpha = 0.10f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (isSelected) 1.8.dp else 0.dp,
                color = if (isSelected) EmeraldPrimary else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .testTag(testTag)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = flag,
                    fontSize = 24.sp
                )

                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) EmeraldPrimary else MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(26.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(EmeraldPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
