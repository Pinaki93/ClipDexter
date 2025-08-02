package dev.pinaki.clipdexter.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.pinaki.clipdexter.services.ClipboardService
import org.koin.core.context.GlobalContext

@Composable
fun KoinExample() {
    // Get service from Koin manually
    val clipboardService: ClipboardService = remember { GlobalContext.get().get() }
    
    var clipboardText by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Koin Dependency Injection Example",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Text(
            text = "Current clipboard text:",
            style = MaterialTheme.typography.bodyLarge
        )
        
        Text(
            text = clipboardText.ifEmpty { "No text in clipboard" },
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(8.dp)
        )
        
        Button(
            onClick = { clipboardText = clipboardService.getClipboardText() }
        ) {
            Text("Refresh Clipboard Text")
        }
        
        Button(
            onClick = { 
                clipboardService.copyToClipboard("Hello from Koin!")
            }
        ) {
            Text("Copy Test Text")
        }
    }
} 