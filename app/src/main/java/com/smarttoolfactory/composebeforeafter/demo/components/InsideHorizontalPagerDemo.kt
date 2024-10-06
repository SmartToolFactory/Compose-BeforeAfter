package com.smarttoolfactory.composebeforeafter.demo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.beforeafter.BeforeAfterImage
import com.smarttoolfactory.beforeafter.OverlayStyle

@Composable
internal fun InsideHorizontalPagerDemo(
    beforeImage: ImageBitmap,
    afterImage: ImageBitmap,
    contentScale: ContentScale,
) {
    val pagerState = rememberPagerState { 3 }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { currentPage ->
                pagerState.animateScrollToPage(currentPage)
            }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier =
            Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxSize()
                .background(Color.Black),
    ) {
        val emptySpaceBetweenPages = 40.dp
        HorizontalPager(
            state = pagerState,
            pageSpacing = emptySpaceBetweenPages,
            contentPadding = PaddingValues(horizontal = emptySpaceBetweenPages),
        ) {
            BeforeAfterImage(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .aspectRatio(4 / 3f),
                beforeImage = beforeImage,
                afterImage = afterImage,
                contentScale = contentScale,
                onProgressStart = { println("Slider move: Start") },
                onProgressEnd = { println("Slider move: End") },
            )
        }
    }
}
