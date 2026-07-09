package com.smarttoolfactory.beforeafter

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ZoomStateTest {

    @Test
    fun `concurrent gesture updates preserve both pans`() = runBlocking {
        val state = ZoomState(zoomEnabled = false, panEnabled = true)

        coroutineScope {
            repeat(2) {
                launch {
                    state.updateZoomState(
                        size = IntSize(100, 100),
                        gesturePan = Offset(10f, 0f),
                        gestureZoom = 1f,
                    )
                }
            }
        }

        assertEquals(20f, state.pan.x, 0f)
    }
}
