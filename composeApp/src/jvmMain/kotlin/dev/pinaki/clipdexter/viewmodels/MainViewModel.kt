package dev.pinaki.clipdexter.viewmodels

import androidx.lifecycle.ViewModel
import dev.pinaki.clipdexter.services.ClipboardService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val clipboardService: ClipboardService
) : ViewModel() {
    
    private val _clipboardText = MutableStateFlow("")
    val clipboardText: StateFlow<String> = _clipboardText.asStateFlow()
    
    fun updateClipboardText() {
        _clipboardText.value = clipboardService.getClipboardText()
    }
    
    fun copyToClipboard(text: String): Boolean {
        return clipboardService.copyToClipboard(text)
    }
} 