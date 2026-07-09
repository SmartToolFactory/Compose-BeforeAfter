package com.smarttoolfactory.composebeforeafter.demo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.beforeafter.BeforeAfterImage
import com.smarttoolfactory.beforeafter.rememberBeforeAfterState

@Composable
internal fun InsideHorizontalPagerDemo(
    beforeImage: ImageBitmap,
    afterImage: ImageBitmap,
    contentScale: ContentScale,
) {
    val pagerState = rememberPagerState { 3 }

    Box(
        contentAlignment = Alignment.Center,
        modifier =
            Modifier
                .border(1.dp, MaterialTheme.colorScheme.inverseSurface, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .fillMaxSize()
                .background(Color.Black),
    ) {
        val emptySpaceBetweenPages = 40.dp
        HorizontalPager(
            modifier = Modifier.horizontalPagerNudge(pagerState),
            state = pagerState,
            pageSpacing = emptySpaceBetweenPages,
            contentPadding = PaddingValues(horizontal = emptySpaceBetweenPages),
        ) {
            BeforeAfterImage(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .aspectRatio(4 / 3f),
                state = rememberBeforeAfterState(),
                beforeImage = beforeImage,
                afterImage = afterImage,
                contentScale = contentScale,
                onProgressStart = { println("Slider move: Start") },
                onProgressEnd = { println("Slider move: End") },
            )
        }
    }
}
