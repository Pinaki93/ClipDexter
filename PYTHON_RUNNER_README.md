# Python Code Runner for ClipDexter

This application now includes a Python code runner that allows you to execute Python scripts directly from the Compose UI with automatic clipboard integration and output copying.

## Features

- **Python Code Execution**: Execute Python 3 scripts directly from the UI
- **Automatic Clipboard Integration**: Clipboard content is automatically available as `$clip` variable
- **Last Line Extraction**: Automatically extracts the last line from Python output
- **Automatic Clipboard Copy**: The last line of output is automatically copied to system clipboard
- **Real-time Output**: See the execution results in a console-style output area
- **Python Availability Check**: The app automatically detects if Python 3 is installed
- **Error Handling**: Comprehensive error handling for execution failures
- **Modern UI**: Clean, Material Design 3 interface

## Requirements

- Python 3.x installed on your system
- The app will automatically detect if Python is available

## How to Use

1. **Launch the Application**: Run the application using `./gradlew run`

2. **Check Python Status**: The app will show whether Python 3 is available on your system

3. **Copy Content to Clipboard**: Copy any text to your system clipboard that you want to process

4. **Enter Python Code**: 
   - Type or paste your Python code in the "Python Code" text area
   - Use `$clip` to access the clipboard content in your Python script
   - The text area supports multi-line code with proper syntax highlighting

5. **Execute Code**: 
   - Click the "Run" button
   - The app will automatically capture clipboard content and inject it as `$clip` variable
   - Your Python script will execute with access to the clipboard content

6. **View Results**: 
   - Successful executions will show the output with a green checkmark
   - Errors will be displayed with a red X and error details

7. **Automatic Clipboard Copy**:
   - After successful execution, the last line of output is automatically extracted
   - The last line is automatically copied to your system clipboard
   - A success message will appear confirming the copy operation

## Example Python Code with Clipboard Integration

Here are some examples you can try:

### Example 1: Basic Clipboard Access
**Clipboard Content:** `Hello World`

**Python Code:**
```python
print("Clipboard content:")
print($clip)
print("Length:", len($clip))
print("Uppercase:", $clip.upper())
```

**Result:** 
```
Clipboard content:
Hello World
Length: 11
Uppercase: HELLO WORLD
```
**Last Line:** `Uppercase: HELLO WORLD` (automatically copied to clipboard)

### Example 2: Text Processing
**Clipboard Content:** `apple,banana,orange,grape`

**Python Code:**
```python
fruits = $clip.split(',')
print("Fruits:", fruits)
print("Count:", len(fruits))
print("Sorted:", sorted(fruits))
```

**Result:**
```
Fruits: ['apple', 'banana', 'orange', 'grape']
Count: 4
Sorted: ['apple', 'banana', 'grape', 'orange']
```
**Last Line:** `Sorted: ['apple', 'banana', 'grape', 'orange']` (automatically copied to clipboard)

### Example 3: Mathematical Operations
**Clipboard Content:** `10,20,30,40,50`

**Python Code:**
```python
numbers = [int(x) for x in $clip.split(',')]
print("Numbers:", numbers)
print("Sum:", sum(numbers))
print("Average:", sum(numbers) / len(numbers))
print("Result:", sum(numbers))
```

**Result:**
```
Numbers: [10, 20, 30, 40, 50]
Sum: 150
Average: 30.0
Result: 150
```
**Last Line:** `Result: 150` (automatically copied to clipboard)

### Example 4: JSON Processing
**Clipboard Content:** `{"name": "John", "age": 30, "city": "New York"}`

**Python Code:**
```python
import json
data = json.loads($clip)
print("Name:", data['name'])
print("Age:", data['age'])
print("City:", data['city'])
print("JSON processed successfully")
```

**Result:**
```
Name: John
Age: 30
City: New York
JSON processed successfully
```
**Last Line:** `JSON processed successfully` (automatically copied to clipboard)

## Technical Details

### Architecture

- **PythonExecutor**: Handles Python script execution using ProcessBuilder with clipboard integration and last line extraction
- **PythonRunnerUI**: Compose UI component for the code editor and console with automatic clipboard functionality
- **ClipboardUtil**: Utility class for system clipboard operations
- **ExecutionResult**: Sealed class for handling success/error states with last line data

### Clipboard Integration Flow

1. User copies content to system clipboard
2. User enters Python code with `$clip` variable
3. User clicks "Run" button
4. PythonExecutor captures clipboard content
5. `$clip` is replaced with the actual clipboard content (properly escaped)
6. Python script executes with access to clipboard data
7. Output is captured and displayed
8. Last line is automatically copied to clipboard

### $clip Variable Injection

The system automatically injects clipboard content as the `$clip` variable:
- Clipboard content is captured at execution time
- Content is properly escaped for Python string literals
- `$clip` is replaced with the actual clipboard content
- Complex clipboard types are handled as strings
- Empty clipboard results in empty string

### Automatic Clipboard Functionality

- **Automatic Copy**: Last line is copied to clipboard immediately after successful execution
- **Copy to Clipboard**: Uses Java AWT Toolkit to access system clipboard
- **Error Handling**: Gracefully handles clipboard access failures
- **Visual Feedback**: Shows success message when copy operation completes
- **Auto-hide**: Success message automatically disappears after 2 seconds
- **No User Interaction**: No manual button clicks required

### Security

- Python scripts are executed in a controlled environment
- Temporary files are automatically cleaned up after execution
- No persistent file system access is granted to executed scripts
- Clipboard content is sanitized and properly escaped
- Clipboard operations are isolated and don't affect system security

### Error Handling

The application handles various error scenarios:
- Python not installed
- Syntax errors in Python code
- Runtime errors during execution
- Process execution failures
- Clipboard access failures
- Invalid clipboard content

## Building and Running

```bash
# Build the project
./gradlew build

# Run the application
./gradlew run
```

## Troubleshooting

1. **Python Not Found**: Make sure Python 3 is installed and accessible via `python3` command
2. **Permission Errors**: Ensure the application has permission to create temporary files
3. **Long-running Scripts**: The UI will show a loading indicator during execution
4. **Clipboard Issues**: Ensure the application has clipboard access permissions
5. **Last Line Not Extracted**: Check that your Python code produces output and the last line is not empty
6. **$clip Not Working**: Make sure you have content in your clipboard before running the script
7. **Encoding Issues**: Clipboard content is handled as UTF-8 text

## Use Cases

The clipboard integration is particularly useful for:
- **Data Processing**: Process copied data from spreadsheets, databases, or web pages
- **Text Transformation**: Convert copied text formats (case changes, formatting, etc.)
- **API Testing**: Process copied JSON responses or API data
- **Calculations**: Perform calculations on copied numerical data
- **Debugging**: Process copied error messages or log data
- **Automation**: Transform copied content for use in other applications
- **Workflow Integration**: Seamlessly process data between different tools

## Workflow Benefits

- **Zero Setup**: No need to manually paste or type input data
- **Immediate Processing**: Clipboard content is instantly available in Python
- **Consistent Results**: Always gets the most relevant line (the final result)
- **Time Saving**: Eliminates the need to manually copy/paste data
- **Error Prevention**: No risk of copying wrong data or typos
- **Seamless Integration**: Works with any application that can copy to clipboard

## Future Enhancements

Potential improvements for the Python runner:
- Syntax highlighting for Python code
- Code completion and IntelliSense
- Support for Python packages and virtual environments
- File upload/download capabilities
- Interactive Python shell mode
- Multiple input/output formats (JSON, CSV, etc.)
- Input validation and formatting
- Custom clipboard formats (rich text, images, etc.)
- Clipboard history for multiple last lines
- Configurable clipboard behavior (copy all output, specific lines, etc.)
- Support for multiple clipboard variables ($clip1, $clip2, etc.)
- Clipboard content preview before execution 