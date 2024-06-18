package com.smarttoolfactory.composebeforeafter.demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttoolfactory.composebeforeafter.R

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