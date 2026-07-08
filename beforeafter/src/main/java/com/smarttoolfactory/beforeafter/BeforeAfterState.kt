package com.smarttoolfactory.beforeafter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

/**
 * State holder for the uncontrolled before/after APIs.
 *
 * Progress is expressed as a percentage in the inclusive range 0..100.
 */
@Stable
class BeforeAfterState internal constructor(initialProgress: Float) {
    private var value by mutableFloatStateOf(initialProgress.coerceIn(0f, 100f))

    var progress: Float
        get() = value
        set(value) {
            this.value = value.coerceIn(0f, 100f)
        }
}

/** Creates and remembers [BeforeAfterState]. */
@Composable
fun rememberBeforeAfterState(initialProgress: Float = 50f): BeforeAfterState =
    remember { BeforeAfterState(initialProgress) }
