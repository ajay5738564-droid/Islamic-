package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.Ayah
import com.example.ui.AudioPlaybackState
import com.example.ui.theme.GoldLight

@Composable
fun AudioMiniPlayerBar(
    currentAyah: Ayah?,
    surahName: String,
    playbackState: AudioPlaybackState,
    onPlayPauseClick: () -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (currentAyah == null || playbackState == AudioPlaybackState.IDLE) return

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .testTag("audio_mini_player_bar"),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Reciting: $surahName (Verse ${currentAyah.numberInSurah})",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Mishary Rashid Alafasy",
                    style = MaterialTheme.typography.labelSmall,
                    color = GoldLight
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (playbackState == AudioPlaybackState.BUFFERING) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(28.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    IconButton(
                        onClick = onPlayPauseClick,
                        modifier = Modifier.testTag("audio_mini_play_pause")
                    ) {
                        Icon(
                            imageVector = if (playbackState == AudioPlaybackState.PLAYING) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = "Play/Pause",
                            tint = Color.White
                        )
                    }
                }

                IconButton(
                    onClick = onCloseClick,
                    modifier = Modifier.testTag("audio_mini_close")
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Player",
                        tint = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}
