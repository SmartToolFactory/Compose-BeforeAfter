package com.smarttoolfactory.beforeafter.util

/**
 * [Linear Interpolation](https://en.wikipedia.org/wiki/Linear_interpolation) function that moves
 * an amount from its current position toward the start and end values.
 * @param start of interval
 * @param end of interval
 * @param amount a value in the closed unit interval [0, 1]
 */
internal fun lerp(start: Float, end: Float, amount: Float): Float {
    return (1 - amount) * start + amount * end
}

/**
 * Scale a value from the start1..end1 range to the start2..end2 range.

 */
internal fun scale(start1: Float, end1: Float, pos: Float, start2: Float, end2: Float) =
    lerp(start2, end2, calculateFraction(start1, end1, pos))

/**
 * Scale x.start, x.endInclusive from a1..b1 range to a2..b2 range
 */
internal fun scale(
    start1: Float,
    end1: Float,
    range: ClosedFloatingPointRange<Float>,
    start2: Float,
    end2: Float
) =
    scale(start1, end1, range.start, start2, end2)..scale(
        start1,
        end1,
        range.endInclusive,
        start2,
        end2
    )


/**
 * Calculate a value's fraction between start and end, coerced into the 0f..1f range.
 */
fun calculateFraction(start: Float, end: Float, pos: Float) =
    (if (end - start == 0f) 0f else (pos - start) / (end - start)).coerceIn(0f, 1f)
