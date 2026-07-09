package com.smarttoolfactory.composebeforeafter.demo.components

import android.graphics.Rect as AndroidRect
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Immutable
internal data class HorizontalPagerNudgeConfig(
    val enabled: Boolean = true,
    val count: Int = 2,
    val distance: Dp = 32.dp,
    val initialDelayMillis: Long = 350L,
    val pauseMillis: Long = 180L,
    val betweenNudgesMillis: Long = 350L,
    val direction: Float = 1f,
) {
    init {
        require(count >= 0) { "count must be non-negative" }
        require(distance >= 0.dp) { "distance must be non-negative" }
        require(initialDelayMillis >= 0L) { "initialDelayMillis must be non-negative" }
        require(pauseMillis >= 0L) { "pauseMillis must be non-negative" }
        require(betweenNudgesMillis >= 0L) { "betweenNudgesMillis must be non-negative" }
        require(direction == 1f || direction == -1f) { "direction must be 1f or -1f" }
    }
}

@Composable
internal fun Modifier.horizontalPagerNudge(
    pagerState: PagerState,
    config: HorizontalPagerNudgeConfig = HorizontalPagerNudgeConfig(),
): Modifier {
    val density = LocalDensity.current
    val view = LocalView.current
    var isVisible by remember { mutableStateOf(false) }
    var hasNudged by remember { mutableStateOf(false) }

    LaunchedEffect(pagerState, isVisible, config) {
        if (!config.enabled || !isVisible || hasNudged || config.count == 0) {
            return@LaunchedEffect
        }

        hasNudged = true
        val initialPage = pagerState.currentPage
        val distance = with(density) { config.distance.toPx() * config.direction }

        delay(config.initialDelayMillis)
        repeat(config.count) { index ->
            if (pagerState.currentPage != initialPage || pagerState.isScrollInProgress) {
                return@LaunchedEffect
            }

            pagerState.animateScrollBy(distance)
            delay(config.pauseMillis)

            if (pagerState.currentPage != initialPage || pagerState.isScrollInProgress) {
                return@LaunchedEffect
            }

            pagerState.animateScrollBy(-distance)
            if (index < config.count - 1) {
                delay(config.betweenNudgesMillis)
            }
        }
    }

    return onGloballyPositioned { coordinates ->
        val visibleFrame = AndroidRect()
        view.getWindowVisibleDisplayFrame(visibleFrame)
        val bounds = coordinates.boundsInWindow()
        isVisible =
            coordinates.isAttached &&
                bounds.left < visibleFrame.right &&
                bounds.right > visibleFrame.left &&
                bounds.top < visibleFrame.bottom &&
                bounds.bottom > visibleFrame.top
    }
}
