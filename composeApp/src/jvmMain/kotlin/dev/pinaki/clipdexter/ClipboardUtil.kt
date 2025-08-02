package dev.pinaki.clipdexter

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.datatransfer.Clipboard

object ClipboardUtil {
    
    fun copyToClipboard(text: String): Boolean {
        return try {
            val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
            
            // Convert \n sequences to actual newlines
            val processedText = text.replace("\\n", "\n")
            
            val selection = StringSelection(processedText)
            clipboard.setContents(selection, selection)
            true
        } catch (e: Exception) {
            false
        }
    }
    
    fun getClipboardText(): String {
        return try {
            val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
            val transferable = clipboard.getContents(null)
            if (transferable != null && transferable.isDataFlavorSupported(java.awt.datatransfer.DataFlavor.stringFlavor)) {
                transferable.getTransferData(java.awt.datatransfer.DataFlavor.stringFlavor) as String
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }
} 