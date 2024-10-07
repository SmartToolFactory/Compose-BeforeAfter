package com.smarttoolfactory.composebeforeafter.demo.helpers

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource

@Composable
internal fun imageBitmapFromRes(
    @DrawableRes resId: Int,
) = ImageBitmap.imageResource(
    LocalContext.current.resources,
    resId,
)
