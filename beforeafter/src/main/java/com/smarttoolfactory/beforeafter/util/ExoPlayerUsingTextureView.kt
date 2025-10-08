package com.smarttoolfactory.beforeafter.util

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.compose.PlayerSurface
import androidx.media3.ui.compose.SURFACE_TYPE_TEXTURE_VIEW

@OptIn(UnstableApi::class)
@Composable
fun ExoPlayerUsingTextureView(modifier: Modifier = Modifier, uri: String) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()

    LaunchedEffect(exoPlayer, uri) {
        with(exoPlayer) {
            setMediaItem(MediaItem.fromUri(uri))
            repeatMode = Player.REPEAT_MODE_ALL
            prepare()
            playWhenReady = true
        }
    }

    PlayerSurface(
        player = exoPlayer,
        modifier = modifier,
        surfaceType = SURFACE_TYPE_TEXTURE_VIEW,
    )

    // Clean up the ExoPlayer when the composable is disposed
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
}




