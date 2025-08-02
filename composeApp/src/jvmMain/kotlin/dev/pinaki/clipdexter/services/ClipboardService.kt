package dev.pinaki.clipdexter.services

import dev.pinaki.clipdexter.ClipboardUtil

interface ClipboardService {
    fun copyToClipboard(text: String): Boolean
    fun getClipboardText(): String
}

class ClipboardServiceImpl : ClipboardService {
    override fun copyToClipboard(text: String): Boolean {
        return ClipboardUtil.copyToClipboard(text)
    }
    
    override fun getClipboardText(): String {
        return ClipboardUtil.getClipboardText()
    }
} 