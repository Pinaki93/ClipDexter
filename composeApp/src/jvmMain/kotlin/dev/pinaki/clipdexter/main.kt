package dev.pinaki.clipdexter

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.pinaki.clipdexter.di.KoinHelper

fun main() = application {
    // Initialize Koin
    KoinHelper.initKoin()
    
    Window(
        onCloseRequest = ::exitApplication,
        title = "ClipDexter",
    ) {
        App()
    }
}