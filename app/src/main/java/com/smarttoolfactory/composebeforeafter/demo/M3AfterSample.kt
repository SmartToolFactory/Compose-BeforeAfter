package com.smarttoolfactory.composebeforeafter.demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.composebeforeafter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun M3AfterSample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(3.dp, Color(0xffD81B60), RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { }) {
            Text("Button")
        }
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { }) {
            Text("OutlinedButton")
        }
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedCard {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.landscape5),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Image inside Card",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row {
            var checked by remember { mutableStateOf(true) }

            Spacer(modifier = Modifier.width(10.dp))
            Switch(checked = checked, onCheckedChange = { checked = it })
            Spacer(modifier = Modifier.width(10.dp))
            Checkbox(checked = checked, onCheckedChange = { checked = it })
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            FloatingActionButton(onClick = { }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(10.dp))

            ExtendedFloatingActionButton(onClick = { }) {
                Text(text = "Extended FAB")
            }
        }

        Row {
            AssistChip(onClick = { },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "")
                },
                label = {
                    Text(text = "AssistChip")
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
            FilterChip(
                selected = true,
                onClick = { },
                label = {
                    Text("FilterChip")
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
            InputChip(
                selected = true,
                onClick = {},
                label = {
                    Text("InputChip")
                }
            )
        }
    }
}