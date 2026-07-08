package com.smarttoolfactory.beforeafter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.foundation.layout.size
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@Suppress("DEPRECATION")
class BeforeAfterImageTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val before = ImageBitmap(100, 100)
    private val after = ImageBitmap(100, 100)

    @Test
    fun controlledApi_reportsDraggedProgress() {
        var progress by mutableFloatStateOf(50f)
        var lastCallback: Float? = null

        composeRule.setContent {
            BeforeAfterImage(
                modifier = Modifier.testTag("comparison").then(Modifier.size(200.dp)),
                beforeImage = before,
                afterImage = after,
                progress = progress,
                onProgressChange = {
                    lastCallback = it
                    progress = it
                },
                contentDescription = "comparison",
            )
        }

        composeRule.onNodeWithTag("comparison")
            .performTouchInput {
                down(Offset(100f, 100f))
                moveTo(Offset(150f, 100f))
                up()
            }

        composeRule.runOnIdle {
            assertEquals(75f, lastCallback ?: error("No progress callback"), 1f)
        }
    }

    @Test
    fun uncontrolledApi_reportsLifecycleCallbacks() {
        var started: Float? = null
        var ended: Float? = null

        composeRule.setContent {
            BeforeAfterImage(
                modifier = Modifier.testTag("comparison").then(Modifier.size(200.dp)),
                beforeImage = before,
                afterImage = after,
                onProgressStart = { started = it },
                onProgressEnd = { ended = it },
            )
        }

        composeRule.onNodeWithTag("comparison").performTouchInput {
            down(Offset(100f, 100f))
            moveTo(Offset(150f, 100f))
            up()
        }

        composeRule.runOnIdle {
            assertEquals(50f, started ?: error("No start callback"), 1f)
            assertEquals(75f, ended ?: error("No end callback"), 1f)
        }
    }
}
