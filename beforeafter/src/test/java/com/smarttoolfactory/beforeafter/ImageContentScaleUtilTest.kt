package com.smarttoolfactory.beforeafter

import com.smarttoolfactory.beforeafter.util.getScaledBitmapRect
import org.junit.Assert.assertEquals
import org.junit.Test

class ImageContentScaleUtilTest {

    @Test
    fun `returns full bitmap rect when image and container match`() {
        val rect = getScaledBitmapRect(
            boxWidth = 200,
            boxHeight = 100,
            imageWidth = 200f,
            imageHeight = 100f,
            bitmapWidth = 200,
            bitmapHeight = 100,
        )

        assertEquals(0, rect.left)
        assertEquals(0, rect.top)
        assertEquals(200, rect.width)
        assertEquals(100, rect.height)
    }

    @Test
    fun `returns centered source rect for cropped image`() {
        val rect = getScaledBitmapRect(
            boxWidth = 100,
            boxHeight = 100,
            imageWidth = 200f,
            imageHeight = 100f,
            bitmapWidth = 200,
            bitmapHeight = 100,
        )

        assertEquals(50, rect.left)
        assertEquals(0, rect.top)
        assertEquals(100, rect.width)
        assertEquals(100, rect.height)
    }

    @Test
    fun `never returns an empty rect for zero dimensions`() {
        val rect = getScaledBitmapRect(
            boxWidth = 0,
            boxHeight = 0,
            imageWidth = 0f,
            imageHeight = 0f,
            bitmapWidth = 20,
            bitmapHeight = 10,
        )

        assertEquals(20, rect.width)
        assertEquals(10, rect.height)
    }
}
