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
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun M2BeforeSample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .border(3.dp, Color(0xff9C27B0), RoundedCornerShape(10.dp))
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

        Card {
            Column {
                Image(
                    painter = painterResource(id = R.drawable.landscape5_before),
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
            ExtendedFloatingActionButton(text = {
                Text(text = "Extended FAB")
            }, onClick = { })
        }

        Chip(onClick = { }) {
            Text("Chip")
        }
    }
}