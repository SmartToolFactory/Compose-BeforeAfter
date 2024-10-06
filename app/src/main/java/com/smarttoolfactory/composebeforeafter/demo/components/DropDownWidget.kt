package com.smarttoolfactory.composebeforeafter.demo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.smarttoolfactory.composebeforeafter.demo.helpers.noRippleClickable

@Composable
fun DropDownWidget(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    outlineColor: Color = Color.Transparent,
    outlineStrokeWidth: Dp = 1.dp,
    padding: Dp = 16.dp,
    options: List<String>,
    selectedOption: String,
    onSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedOptionIndex = options.indexOf(selectedOption).coerceIn(0, options.size - 1)
    var selectedIndex by remember { mutableIntStateOf(selectedOptionIndex) }
    Box(
        modifier =
            modifier
                .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                .border(
                    width = outlineStrokeWidth,
                    color = outlineColor,
                    shape = RoundedCornerShape(8.dp),
                ).noRippleClickable { expanded = !expanded }
                .wrapContentSize(),
        contentAlignment = Alignment.TopStart,
    ) {
        Text(
            text = options[selectedIndex],
            modifier =
                Modifier
                    .padding(padding)
                    .fillMaxWidth()
                    .noRippleClickable(onClick = { expanded = true }),
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(),
        ) {
            options.forEachIndexed { index, s ->
                DropdownMenuItem(
                    text = { Text(text = s) },
                    onClick = {
                        selectedIndex = index
                        expanded = false
                        onSelected(s)
                    },
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    DropDownWidget(
        backgroundColor = Color.LightGray,
        outlineColor = Color.Gray,
        outlineStrokeWidth = 2.dp,
        selectedOption = "Option 2",
        onSelected = {},
        options = listOf("Option 1", "Option 2", "Option 3"),
    )
}
