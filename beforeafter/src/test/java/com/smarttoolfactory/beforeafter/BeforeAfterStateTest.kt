package com.smarttoolfactory.beforeafter

import org.junit.Assert.assertEquals
import org.junit.Test

class BeforeAfterStateTest {

    @Test
    fun `clamps initial and updated progress`() {
        val state = BeforeAfterState(initialProgress = 150f)

        assertEquals(100f, state.progress, 0f)

        state.progress = -10f

        assertEquals(0f, state.progress, 0f)
    }
}
