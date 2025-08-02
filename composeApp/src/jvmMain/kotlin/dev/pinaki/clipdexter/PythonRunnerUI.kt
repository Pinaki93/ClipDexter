package dev.pinaki.clipdexter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun PythonRunnerUI() {
    var pythonCode by remember { mutableStateOf("") }
    var consoleOutput by remember { mutableStateOf("") }
    var lastLine by remember { mutableStateOf("") }
    var isExecuting by remember { mutableStateOf(false) }
    var pythonAvailable by remember { mutableStateOf(false) }
    var showCopySuccess by remember { mutableStateOf(false) }
    
    val pythonExecutor = remember { PythonExecutor() }
    val coroutineScope = rememberCoroutineScope()
    
    // Check if Python is available on startup
    LaunchedEffect(Unit) {
        pythonAvailable = pythonExecutor.isPythonAvailable()
    }
    
    // Show copy success message for 2 seconds
    LaunchedEffect(showCopySuccess) {
        if (showCopySuccess) {
            kotlinx.coroutines.delay(2000)
            showCopySuccess = false
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "Python Code Runner",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        // Python availability status
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (pythonAvailable) Color(0xFFE8F5E8) else Color(0xFFFFEBEE)
            )
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (pythonAvailable) "✓ Python 3 is available" else "✗ Python 3 not found",
                    color = if (pythonAvailable) Color(0xFF2E7D32) else Color(0xFFC62828)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Code input area
        Text(
            text = "Python Code:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            BasicTextField(
                value = pythonCode,
                onValueChange = { pythonCode = it },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        if (pythonCode.isEmpty()) {
                            Text(
                                text = "Enter your Python code here...\nUse \$clip to access clipboard content",
                                style = TextStyle(
                                    fontFamily = FontFamily.Monospace,
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Run button
        Button(
            onClick = {
                if (pythonCode.isNotBlank()) {
                    coroutineScope.launch {
                        isExecuting = true
                        consoleOutput = "Executing Python code...\n"
                        
                        val result = pythonExecutor.executePythonCode(pythonCode)
                        when (result) {
                            is ExecutionResult.Success -> {
                                consoleOutput = "✓ Execution successful:\n\n${result.output}"
                                lastLine = result.lastLine
                                
                                // Automatically copy last line to clipboard if it's not empty
                                if (result.lastLine.isNotBlank()) {
                                    if (ClipboardUtil.copyToClipboard(result.lastLine)) {
                                        showCopySuccess = true
                                    }
                                }
                            }
                            is ExecutionResult.Error -> {
                                consoleOutput = "✗ Execution failed:\n\n${result.message}"
                                lastLine = ""
                            }
                        }
                        isExecuting = false
                    }
                }
            },
            enabled = pythonAvailable && pythonCode.isNotBlank() && !isExecuting,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isExecuting) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(if (isExecuting) "Running..." else "Run")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Console output area
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Console Output:",
                style = MaterialTheme.typography.titleMedium
            )
            
            // Show copy success message
            if (showCopySuccess) {
                Text(
                    text = "✓ Last line copied to clipboard!",
                    color = Color(0xFF2E7D32),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1E1E1E))
                    .padding(16.dp)
            ) {
                Text(
                    text = consoleOutput.ifEmpty { "Output will appear here..." },
                    style = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp,
                        color = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                )
            }
        }
    }
} 