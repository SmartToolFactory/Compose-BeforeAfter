package com.smarttoolfactory.composebeforeafter.demo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

@UnstableApi
@Composable
fun MyPlayer(modifier: Modifier, uri: String) {
    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()
    val playerView = remember {
        PlayerView(context)
    }


    println("🚀 MyPlayer URI $uri, player: $exoPlayer, playerView: $playerView")

    LaunchedEffect(exoPlayer, uri) {
        with(exoPlayer) {
            setMediaItem(MediaItem.fromUri(uri))
            repeatMode = Player.REPEAT_MODE_ALL
            prepare()
            playWhenReady = true
        }
        
        with(playerView) {
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
            player = exoPlayer
        }
    }

    AndroidView(
        modifier = modifier,
        factory = {
            playerView
        }
    )
}