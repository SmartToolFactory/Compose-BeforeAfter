package com.smarttoolfactory.composebeforeafter.demo

import androidx.annotation.OptIn
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.UnstableApi
import com.smarttoolfactory.beforeafter.AfterLabel
import com.smarttoolfactory.beforeafter.BeforeAfterLayout
import com.smarttoolfactory.beforeafter.BeforeLabel
import com.smarttoolfactory.beforeafter.ContentOrder
import com.smarttoolfactory.beforeafter.OverlayStyle
import com.smarttoolfactory.beforeafter.util.ExoPlayerUsingTextureView
import com.smarttoolfactory.composebeforeafter.R
import com.smarttoolfactory.composebeforeafter.demo.components.M2BeforeSample
import com.smarttoolfactory.composebeforeafter.demo.components.M3AfterSample
import com.smarttoolfactory.composebeforeafter.demo.helpers.BottomSpacer
import com.smarttoolfactory.composebeforeafter.demo.helpers.SectionDividerSpace
import com.smarttoolfactory.composebeforeafter.demo.helpers.SectionTitle
import com.smarttoolfactory.composebeforeafter.demo.helpers.imageBitmapFromRes
import kotlin.math.roundToInt

@OptIn(UnstableApi::class)
@Composable
internal fun BeforeAfterLayoutDemo() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        val imageBefore = imageBitmapFromRes(R.drawable.image_before_after_shoes_a)
        val imageAfter = imageBitmapFromRes(R.drawable.image_before_after_shoes_b)
        val imageBefore2 = imageBitmapFromRes(R.drawable.landscape5_before)
        val imageAfter2 = imageBitmapFromRes(R.drawable.landscape5)

        SectionTitle(text = "Customization")

        BeforeAfterLayout(
            modifier = Modifier
                .shadow(1.dp, RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .aspectRatio(4 / 3f),
            beforeContent = {
                DemoImage(imageBitmap = imageBefore)
            },
            afterContent = {
                DemoImage(imageBitmap = imageAfter)
            },
            overlayStyle = OverlayStyle(
                dividerColor = Color(0xffF44336),
                dividerWidth = 2.dp,
                thumbShape = CutCornerShape(8.dp),
                thumbBackgroundColor = Color.Red,
                thumbTintColor = Color.White,
            ),
            onProgressStart = { progress -> println("Slider move: Start | Progress: $progress") },
            onProgressEnd = { progress -> println("Slider move: End | Progress: $progress") },
        )


        SectionDividerSpace()

        SectionTitle(text = "Zoom (Pinch gesture)")

        BeforeAfterLayout(
            modifier = Modifier
                .shadow(1.dp, RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .aspectRatio(4 / 3f),
            beforeContent = {
                DemoImage(imageBitmap = imageBefore2)
            },
            afterContent = {
                DemoImage(imageBitmap = imageAfter2)
            },
            contentOrder = ContentOrder.AfterBefore,
            onProgressStart = { progress -> println("Slider move: Start | Progress: $progress") },
            onProgressEnd = { progress -> println("Slider move: End | Progress: $progress") },
        )

        SectionDividerSpace()

        val transition: InfiniteTransition = rememberInfiniteTransition()

        // Infinite progress animation
        val progress by transition.animateFloat(
            initialValue = 0f,
            targetValue = 100f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 4000,
                    easing = FastOutSlowInEasing,
                ),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "Progress",
        )

        SectionTitle(text = "Progress animation")

        BeforeAfterLayout(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            progress = progress,
            beforeContent = {
                BeforeComposable(progress)
            },
            afterContent = {
                AfterComposable(progress)
            },
            enableProgressWithTouch = false,
            enableZoom = false,
            beforeLabel = null,
            afterLabel = null,
            overlay = null,
            onProgressStart = { progress -> println("Slider move: Start | Progress: $progress") },
            onProgressEnd = { progress -> println("Slider move: End | Progress: $progress") },
        )

        SectionDividerSpace()

        SectionTitle(text = "Layout")

        BeforeAfterLayout(
            modifier = Modifier.fillMaxWidth(),
            beforeContent = {
                M2BeforeSample()
            },
            afterContent = {
                M3AfterSample()
            },
            beforeLabel = {
                BeforeLabel(text = "Material Design2")
            },
            afterLabel = {
                AfterLabel(text = "Material Design3")
            },
            enableZoom = false,
            overlayStyle = OverlayStyle(thumbPositionPercent = 60f),
        )

        // FIXME There is a bug with Exoplayer2 and setting Modifier.graphicsLayer
        //  If you find an answer feel free to open a PR or answer question below
        // https://stackoverflow.com/questions/73061216/exoplayer2-with-before-after-videos-changes-first-video-when-clip-and-shape-used

        SectionDividerSpace()

        SectionTitle(text = "Video")

        BeforeAfterLayout(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(4 / 3f),
            beforeContent = {
                ExoPlayerUsingTextureView(
                    uri = "asset:///floodplain_dirty.mp4",
                )
            },
            afterContent = {
                ExoPlayerUsingTextureView(
                    uri = "asset:///floodplain_clean.mp4",
                )
            },
            enableZoom = false,
            onProgressStart = { progress -> println("Slider move: Start | Progress: $progress") },
            onProgressEnd = { progress -> println("Slider move: End | Progress: $progress") },
        )

        BottomSpacer()
    }
}

@Composable
private fun DemoImage(imageBitmap: ImageBitmap) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(4 / 3f),
        bitmap = imageBitmap,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
    )
}

@Composable
private fun BeforeComposable(progress: Float) {

    Column(
        modifier = Modifier
            .border(3.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(50))
            .clip(RoundedCornerShape(50))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "${(progress).roundToInt()}%",
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }

}

@Composable
private fun AfterComposable(progress: Float) {
    Column(
        modifier = Modifier
            .border(3.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(50))
            .clip(RoundedCornerShape(50))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "${(progress).roundToInt()}%",
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

