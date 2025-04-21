package com.smarttoolfactory.beforeafter.util

import android.content.Context
import android.graphics.SurfaceTexture
import android.view.Surface
import android.view.TextureView
import androidx.media3.exoplayer.ExoPlayer

/**
 * Extension function for ExoPlayer to create and configure a TextureView for video rendering.
 *
 * This function initializes a TextureView and sets up a SurfaceTextureListener to manage the rendering surface.
 * It ensures proper handling of surface availability, resizing, and destruction, making ExoPlayer compatible with Compose.
 *
 * @receiver ExoPlayer The ExoPlayer instance that will be bound to the TextureView.
 * @param context The Android context used to instantiate the TextureView.
 * @return TextureView A configured TextureView that can be used for rendering video.
 */
fun ExoPlayer.createTextureView(context: Context): TextureView {
    return TextureView(context).apply {
        // Listen for surface events to correctly bind ExoPlayer to TextureView
        surfaceTextureListener = object : TextureView.SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(
                surface: SurfaceTexture,
                width: Int,
                height: Int,
            ) {
                // Bind the TextureView surface to ExoPlayer
                this@createTextureView.setVideoSurface(Surface(surface))
            }

            override fun onSurfaceTextureSizeChanged(
                surface: SurfaceTexture,
                width: Int,
                height: Int,
            ) {
                // Handle changes in TextureView size if needed (e.g., aspect ratio adjustments)
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                // Remove the surface from ExoPlayer to prevent memory leaks
                this@createTextureView.setVideoSurface(null)
                return true // Allow texture cleanup
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
                // Called when a new video frame is available; usually no action needed here
            }
        }
    }
}
