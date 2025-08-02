package dev.pinaki.clipdexter

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.pinaki.clipdexter.navigation.Navigation
import dev.pinaki.clipdexter.viewmodels.UtilitiesViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val utilitiesViewModel: UtilitiesViewModel = koinInject()
            Navigation(utilitiesViewModel)
        }
    }
}