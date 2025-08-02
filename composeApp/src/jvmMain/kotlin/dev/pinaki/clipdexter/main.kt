package dev.pinaki.clipdexter

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ClipDexter",
    ) {
        App()
    }
}