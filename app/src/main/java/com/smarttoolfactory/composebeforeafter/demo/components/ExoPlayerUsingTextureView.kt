package com.smarttoolfactory.composebeforeafter.demo.components

import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
internal fun ExoPlayerUsingTextureView(modifier: Modifier = Modifier, uri: String) {
    val appContext = LocalContext.current.applicationContext
    val exoPlayer = remember(appContext) {
        ExoPlayer.Builder(appContext).build()
    }

    LaunchedEffect(exoPlayer, uri) {
        exoPlayer.setMediaItem(MediaItem.fromUri(uri))
        exoPlayer.repeatMode = Player.REPEAT_MODE_ALL
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    PlayerSurface(
        player = exoPlayer,
        modifier = modifier,
        surfaceType = SURFACE_TYPE_TEXTURE_VIEW,
    )

    DisposableEffect(exoPlayer) {
        onDispose(exoPlayer::release)
    }
}
