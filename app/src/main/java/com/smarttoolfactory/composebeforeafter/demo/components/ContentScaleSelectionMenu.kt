package com.smarttoolfactory.composebeforeafter.demo.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

@Composable
internal fun ContentScaleSelectionMenu(
    selectedContentScale: ContentScale,
    onContentScaleChanged: (ContentScale) -> Unit,
) {
    DropDownWidget(
        outlineColor = Color.LightGray,
        selectedOption = stringFromContentScale(selectedContentScale),
        options =
            listOf(
                "None",
                "Fit",
                "Crop",
                "FillBounds",
                "FillWidth",
                "FillHeight",
                "Inside",
            ),
    ) {
        val contentScale = contentScaleFromString(it)
        onContentScaleChanged(contentScale)
    }
}

private fun stringFromContentScale(contentScale: ContentScale): String =
    when (contentScale) {
        ContentScale.None -> "None"
        ContentScale.Fit -> "Fit"
        ContentScale.Crop -> "Crop"
        ContentScale.FillBounds -> "FillBounds"
        ContentScale.FillWidth -> "FillWidth"
        ContentScale.FillHeight -> "FillHeight"
        ContentScale.Inside -> "Inside"
        else -> "Inside"
    }

private fun contentScaleFromString(stringName: String) =
    when (stringName) {
        "None" -> ContentScale.None
        "Fit" -> ContentScale.Fit
        "Crop" -> ContentScale.Crop
        "FillBounds" -> ContentScale.FillBounds
        "FillWidth" -> ContentScale.FillWidth
        "FillHeight" -> ContentScale.FillHeight
        "Inside" -> ContentScale.Inside
        else -> ContentScale.Inside
    }
