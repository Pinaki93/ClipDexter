package dev.pinaki.clipdexter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException

class PythonExecutor {
    
    suspend fun executePythonScript(code: String, inputText: String): String = withContext(Dispatchers.IO) {
        try {
            // Create a temporary Python file
            val tempFile = File.createTempFile("python_script_", ".py")
            tempFile.deleteOnExit()
            
            // Inject input text as command line argument and write the code to the file
            val modifiedCode = injectInputVariable(code, inputText)
            tempFile.writeText(modifiedCode)
            
            // Execute the Python script
            val processBuilder = ProcessBuilder("python3", tempFile.absolutePath)
            processBuilder.redirectErrorStream(true)
            
            val process = processBuilder.start()
            
            // Read the output
            val output = process.inputStream.bufferedReader().readText()
            
            // Wait for the process to complete
            val exitCode = process.waitFor()
            
            // Clean up the temporary file
            tempFile.delete()
            
            if (exitCode == 0) {
                // Extract the last non-empty line from output
                extractLastLine(output)
            } else {
                "Error: Process exited with code $exitCode: $output"
            }
            
        } catch (e: IOException) {
            "Error: Failed to execute Python: ${e.message}"
        } catch (e: InterruptedException) {
            "Error: Process was interrupted: ${e.message}"
        } catch (e: Exception) {
            "Error: Unexpected error: ${e.message}"
        }
    }
    
    suspend fun executePythonCode(code: String): ExecutionResult = withContext(Dispatchers.IO) {
        try {
            // Get clipboard content
            val clipboardContent = ClipboardUtil.getClipboardText()
            
            // Create a temporary Python file
            val tempFile = File.createTempFile("python_script_", ".py")
            tempFile.deleteOnExit()
            
            // Inject clipboard content as $clip variable and write the code to the file
            val modifiedCode = injectClipboardVariable(code, clipboardContent)
            tempFile.writeText(modifiedCode)
            
            // Execute the Python script
            val processBuilder = ProcessBuilder("python3", tempFile.absolutePath)
            processBuilder.redirectErrorStream(true)
            
            val process = processBuilder.start()
            
            // Read the output
            val output = process.inputStream.bufferedReader().readText()
            
            // Wait for the process to complete
            val exitCode = process.waitFor()
            
            // Clean up the temporary file
            tempFile.delete()
            
            if (exitCode == 0) {
                // Extract the last non-empty line from output
                val lastLine = extractLastLine(output)
                ExecutionResult.Success(output, lastLine)
            } else {
                ExecutionResult.Error("Process exited with code $exitCode: $output")
            }
            
        } catch (e: IOException) {
            ExecutionResult.Error("Failed to execute Python: ${e.message}")
        } catch (e: InterruptedException) {
            ExecutionResult.Error("Process was interrupted: ${e.message}")
        } catch (e: Exception) {
            ExecutionResult.Error("Unexpected error: ${e.message}")
        }
    }
    
    private fun injectInputVariable(code: String, inputText: String): String {
        // Escape the input text for Python string
        val escapedInput = inputText
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t")
        
        // Replace INPUT_PLACEHOLDER with the actual input text
        return code.replace("INPUT_PLACEHOLDER", "\"$escapedInput\"")
    }
    
    private fun injectClipboardVariable(code: String, clipboardContent: String): String {
        // Escape the clipboard content for Python string
        val escapedClipboard = clipboardContent
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t")
        
        // Replace \$clip with the actual clipboard content
        return code.replace("\$clip", "\"$escapedClipboard\"")
    }
    
    private fun extractLastLine(output: String): String {
        return output.trim()
            .split("\n")
            .filter { it.isNotBlank() }
            .lastOrNull() ?: ""
    }
    
    fun isPythonAvailable(): Boolean {
        return try {
            val process = ProcessBuilder("python3", "--version").start()
            val exitCode = process.waitFor()
            exitCode == 0
        } catch (e: Exception) {
            false
        }
    }
}

sealed class ExecutionResult {
    data class Success(val output: String, val lastLine: String = "") : ExecutionResult()
    data class Error(val message: String) : ExecutionResult()
} 