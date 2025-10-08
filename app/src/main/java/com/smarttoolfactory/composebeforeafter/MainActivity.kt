package com.smarttoolfactory.composebeforeafter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.smarttoolfactory.composebeforeafter.ui.HomeContent
import com.smarttoolfactory.composebeforeafter.ui.theme.ComposeBeforeAfterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ComposeBeforeAfterTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                        .navigationBarsPadding(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    HomeContent()
                }
            }
        }
    }
}
