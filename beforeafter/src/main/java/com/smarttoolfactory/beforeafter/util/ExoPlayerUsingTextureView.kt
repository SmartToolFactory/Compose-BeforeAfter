package com.smarttoolfactory.beforeafter.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

@Composable
fun ExoPlayerUsingTextureView(modifier: Modifier = Modifier, uri: String) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()
    val textureView = remember { exoPlayer.createTextureView(context) }

    LaunchedEffect(exoPlayer, uri) {
        with(exoPlayer) {
            setMediaItem(MediaItem.fromUri(uri))
            repeatMode = Player.REPEAT_MODE_ALL
            prepare()
            playWhenReady = true
        }
    }

    AndroidView(
        modifier = modifier,
        factory = { textureView },
    )

    // Clean up the ExoPlayer when the composable is disposed
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
}




