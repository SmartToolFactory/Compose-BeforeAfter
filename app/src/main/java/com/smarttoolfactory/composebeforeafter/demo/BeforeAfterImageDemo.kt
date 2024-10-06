package com.smarttoolfactory.composebeforeafter.demo

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.beforeafter.BeforeAfterImage
import com.smarttoolfactory.beforeafter.ContentOrder
import com.smarttoolfactory.beforeafter.OverlayStyle
import com.smarttoolfactory.composebeforeafter.R
import com.smarttoolfactory.composebeforeafter.demo.components.ContentScaleSelectionMenu
import com.smarttoolfactory.composebeforeafter.demo.helpers.SectionDividerSpace
import com.smarttoolfactory.composebeforeafter.demo.helpers.SectionTitle
import com.smarttoolfactory.composebeforeafter.demo.helpers.imageBitmapFromRes
import kotlin.math.roundToInt

@Composable
internal fun BeforeAfterImageDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val imageBefore = imageBitmapFromRes(R.drawable.image_before_after_shoes_a)
        val imageAfter = imageBitmapFromRes(R.drawable.image_before_after_shoes_b)
        val imageBefore2 = imageBitmapFromRes(R.drawable.landscape5_before)
        val imageAfter2 = imageBitmapFromRes(R.drawable.landscape5)
        val imageBefore3 = imageBitmapFromRes(R.drawable.image_before_after_elements_a)
        val imageAfter3 = imageBitmapFromRes(R.drawable.image_before_after_elements_b)

        SectionTitle(text = "BeforeAfterImage", fontSize = 20.sp)

        var contentScale by remember { mutableStateOf(ContentScale.FillBounds) }
        ContentScaleSelectionMenu(contentScale = contentScale) {
            contentScale = it
        }

        SectionTitle(text = "Order")

        BeforeAfterImage(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .aspectRatio(4 / 3f),
            beforeImage = imageBefore,
            afterImage = imageAfter,
            contentScale = contentScale,
            overlayStyle = OverlayStyle(
                dividerBrush = Brush.verticalGradient(
                    listOf(
                        Color.Red,
                        Color.Blue,
			        ),
		        ),
                dividerWidth = 8.dp,
	        ),
            onProgressStart = { println("Slider move: Start") },
            onProgressEnd = {  println("Slider move: End") },
        )

        SectionDividerSpace()

        BeforeAfterImage(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .aspectRatio(4 / 3f),
            beforeImage = imageBefore,
            afterImage = imageAfter,
            contentOrder = ContentOrder.AfterBefore,
            contentScale = contentScale,
            overlayStyle = OverlayStyle(),
            onProgressStart = { println("Slider move: Start") },
            onProgressEnd = {  println("Slider move: End") },
        )

        SectionTitle(
            text = "Zoom(Pinch gesture)"
        )

        BeforeAfterImage(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .aspectRatio(4 / 3f),
            beforeImage = imageBefore2,
            afterImage = imageAfter2,
            contentOrder = ContentOrder.AfterBefore,
            contentScale = contentScale,
            onProgressStart = { println("Slider move: Start") },
            onProgressEnd = {  println("Slider move: End") },
        )

        SectionTitle(
            text = "Progress animation"
        )

        val transition: InfiniteTransition = rememberInfiniteTransition()

        // Infinite progress animation
        val progress by transition.animateFloat(
            initialValue = 0f,
            targetValue = 100f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 4000,
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Reverse
            ), label = "Infinite progress animation"
        )

        BeforeAfterImage(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .border(3.dp, Color(0xffE91E63), RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .aspectRatio(4 / 3f),
            beforeImage = imageBefore3,
            afterImage = imageAfter3,
            progress = progress,
            onProgressChange = {},
            contentScale = contentScale,
            beforeLabel = {},
            afterLabel = {},
            onProgressStart = { println("Slider move: Start") },
            onProgressEnd = {  println("Slider move: End") },
        ) {
            Text(
                "${(progress).roundToInt()}%",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xff03A9F4)
            )
        }
    }
}
