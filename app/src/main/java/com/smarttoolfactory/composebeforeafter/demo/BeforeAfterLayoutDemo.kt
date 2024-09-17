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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.UnstableApi
import com.smarttoolfactory.beforeafter.AfterLabel
import com.smarttoolfactory.beforeafter.BeforeAfterLayout
import com.smarttoolfactory.beforeafter.BeforeLabel
import com.smarttoolfactory.beforeafter.ContentOrder
import com.smarttoolfactory.beforeafter.OverlayStyle
import com.smarttoolfactory.composebeforeafter.R
import kotlin.math.roundToInt

@OptIn(UnstableApi::class)
@Composable
fun BeforeAfterLayoutDemo() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
    ) {

        val imageBefore = ImageBitmap.imageResource(
            LocalContext.current.resources, R.drawable.image_before_after_shoes_a,
        )

        val imageAfter = ImageBitmap.imageResource(
            LocalContext.current.resources, R.drawable.image_before_after_shoes_b,
        )


        val imageBefore2 = ImageBitmap.imageResource(
            LocalContext.current.resources, R.drawable.landscape5_before,
        )

        val imageAfter2 = ImageBitmap.imageResource(
            LocalContext.current.resources, R.drawable.landscape5,
        )
        Text(
            text = "BeforeAfterLayout",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(8.dp),
        )

        Text(
            text = "Customization",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(8.dp),
        )

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
            onProgressStart = { println("Slider move: Start") },
            onProgressEnd = {  println("Slider move: End") },
        )


        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Zoom(Pinch gesture)",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(8.dp),
        )

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
            onProgressStart = { println("Slider move: Start") },
            onProgressEnd = {  println("Slider move: End") },
        )

        Spacer(modifier = Modifier.height(50.dp))

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
        )


        Text(
            text = "Progress animation",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(8.dp),
        )
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
            onProgressStart = { println("Slider move: Start") },
            onProgressEnd = {  println("Slider move: End") },
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Layout",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(8.dp),
        )

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

        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Video",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(8.dp),
        )
        BeforeAfterLayout(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(4 / 3f),
            beforeContent = {
                MyPlayer(
                    modifier = Modifier
                        .border(3.dp, Color.Red),
                    uri = "asset:///floodplain_dirty.mp4",
                )
            },
            afterContent = {
                MyPlayer(
                    modifier = Modifier
                        .border(3.dp, Color.Yellow),
                    uri = "asset:///floodplain_clean.mp4",
                )
            },
            enableZoom = false,
            onProgressStart = { println("Slider move: Start") },
            onProgressEnd = {  println("Slider move: End") },
        )
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

