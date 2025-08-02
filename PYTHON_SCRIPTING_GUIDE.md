# Python Scripting Guide for ClipDexter

This guide explains how to write Python scripts for the ClipDexter application, which allows you to process clipboard content using Python with automatic input/output handling.

## Table of Contents

1. [Basic Concepts](#basic-concepts)
2. [The `$clip` Variable](#the-clip-variable)
3. [Getting Started](#getting-started)
4. [Common Patterns](#common-patterns)
5. [Data Processing Examples](#data-processing-examples)
6. [Text Manipulation](#text-manipulation)
7. [Mathematical Operations](#mathematical-operations)
8. [JSON and API Data](#json-and-api-data)
9. [Error Handling](#error-handling)
10. [Best Practices](#best-practices)
11. [Troubleshooting](#troubleshooting)

## Basic Concepts

### How It Works

1. **Copy Content**: Copy any text to your system clipboard
2. **Write Python Code**: Use the `$clip` variable to access clipboard content
3. **Run Script**: Click "Run" to execute your Python code
4. **Get Results**: The last line of output is automatically copied to clipboard

### Key Features

- **Automatic Input**: Clipboard content is available as `$clip` variable
- **Automatic Output**: Last line is automatically copied to clipboard
- **Real-time Execution**: See results immediately in the console
- **Error Handling**: Clear error messages for debugging

## The `$clip` Variable

### What is `$clip`?

The `$clip` variable is automatically replaced with your clipboard content when the script runs. It's a string variable that contains whatever text you've copied to your clipboard.

### How to Use `$clip`

```python
# Basic usage
print("Clipboard content:", $clip)

# Check if clipboard is empty
if $clip.strip():
    print("Clipboard has content")
else:
    print("Clipboard is empty")

# Get length of clipboard content
print("Length:", len($clip))
```

### Important Notes

- `$clip` is always a string, even if you copied numbers
- Empty clipboard results in an empty string
- The variable is replaced before Python execution
- You can use it anywhere a string is expected

## Getting Started

### Your First Script

1. **Copy some text** to your clipboard (e.g., "Hello World")
2. **Write this Python code**:

```python
print("Processing clipboard content...")
print("Original:", $clip)
print("Uppercase:", $clip.upper())
print("Length:", len($clip))
```

3. **Click "Run"**
4. **Result**: The last line (`Length: 11`) is automatically copied to clipboard

### Simple Examples

**Example 1: Basic Text Processing**
```python
# Copy "hello world" to clipboard
text = $clip.upper()
print("Uppercase:", text)
```

**Example 2: Word Count**
```python
# Copy "The quick brown fox" to clipboard
words = $clip.split()
print("Word count:", len(words))
```

**Example 3: Character Analysis**
```python
# Copy any text to clipboard
print("Characters:", len($clip))
print("Words:", len($clip.split()))
print("Lines:", len($clip.splitlines()))
```

## Common Patterns

### Pattern 1: Data Validation

```python
# Check if clipboard has valid content
if not $clip.strip():
    print("Error: Clipboard is empty")
elif len($clip) < 3:
    print("Error: Content too short")
else:
    print("Processing:", $clip)
    # Your processing logic here
```

### Pattern 2: Type Conversion

```python
# Try to convert clipboard content to different types
try:
    number = int($clip)
    print("Integer:", number)
except ValueError:
    try:
        number = float($clip)
        print("Float:", number)
    except ValueError:
        print("Not a number:", $clip)
```

### Pattern 3: Conditional Processing

```python
# Process different types of content
content = $clip.strip().lower()

if content.startswith('http'):
    print("URL detected")
elif content.isdigit():
    print("Number detected")
elif ',' in content:
    print("CSV-like data detected")
else:
    print("Plain text detected")
```

## Data Processing Examples

### CSV Data Processing

**Clipboard Content:** `apple,banana,orange,grape`

```python
# Split CSV data
items = $clip.split(',')
print("Items:", items)
print("Count:", len(items))
print("Sorted:", sorted(items))
print("First item:", items[0])
```

### Number Lists

**Clipboard Content:** `10,20,30,40,50`

```python
# Convert to numbers and process
numbers = [int(x.strip()) for x in $clip.split(',')]
print("Numbers:", numbers)
print("Sum:", sum(numbers))
print("Average:", sum(numbers) / len(numbers))
print("Max:", max(numbers))
print("Min:", min(numbers))
```

### Multi-line Text

**Clipboard Content:**
```
Line 1
Line 2
Line 3
```

```python
# Process multi-line text
lines = $clip.splitlines()
print("Line count:", len(lines))
print("Lines:", lines)
print("First line:", lines[0])
print("Last line:", lines[-1])
```

## Text Manipulation

### Case Transformations

```python
# Various case transformations
print("Original:", $clip)
print("Uppercase:", $clip.upper())
print("Lowercase:", $clip.lower())
print("Title case:", $clip.title())
print("Capitalized:", $clip.capitalize())
```

### String Operations

```python
# Common string operations
text = $clip.strip()
print("Original length:", len($clip))
print("Trimmed length:", len(text))
print("Word count:", len(text.split()))
print("Character count:", len(text.replace(" ", "")))
print("Reversed:", text[::-1])
```

### Text Cleaning

```python
# Clean and normalize text
import re

# Remove extra whitespace
cleaned = re.sub(r'\s+', ' ', $clip.strip())
print("Cleaned:", cleaned)

# Remove special characters
alphanumeric = re.sub(r'[^a-zA-Z0-9\s]', '', $clip)
print("Alphanumeric only:", alphanumeric)

# Extract numbers
numbers = re.findall(r'\d+', $clip)
print("Numbers found:", numbers)
```

## Mathematical Operations

### Basic Calculations

**Clipboard Content:** `10,20,30`

```python
# Basic math operations
numbers = [int(x) for x in $clip.split(',')]
print("Numbers:", numbers)
print("Sum:", sum(numbers))
print("Product:", numbers[0] * numbers[1] * numbers[2])
print("Average:", sum(numbers) / len(numbers))
```

### Advanced Math

```python
import math

# Try to convert to number
try:
    number = float($clip)
    print("Number:", number)
    print("Square root:", math.sqrt(number))
    print("Square:", number ** 2)
    print("Logarithm:", math.log(number))
except ValueError:
    print("Not a valid number")
```

### Statistical Operations

```python
# Statistical calculations
numbers = [float(x) for x in $clip.split(',')]
print("Count:", len(numbers))
print("Sum:", sum(numbers))
print("Mean:", sum(numbers) / len(numbers))
print("Min:", min(numbers))
print("Max:", max(numbers))
print("Range:", max(numbers) - min(numbers))
```

## JSON and API Data

### JSON Processing

**Clipboard Content:** `{"name": "John", "age": 30, "city": "New York"}`

```python
import json

# Parse JSON data
try:
    data = json.loads($clip)
    print("Name:", data.get('name'))
    print("Age:", data.get('age'))
    print("City:", data.get('city'))
    print("All keys:", list(data.keys()))
except json.JSONDecodeError:
    print("Invalid JSON format")
```

### API Response Processing

```python
import json

# Process API response
try:
    response = json.loads($clip)
    
    # Handle different response structures
    if 'data' in response:
        data = response['data']
        print("Data found:", len(data) if isinstance(data, list) else "Object")
    elif 'results' in response:
        results = response['results']
        print("Results count:", len(results))
    else:
        print("Response keys:", list(response.keys()))
        
except json.JSONDecodeError:
    print("Not valid JSON")
```

## Error Handling

### Robust Scripts

```python
# Always handle potential errors
try:
    # Your main logic here
    if not $clip.strip():
        print("Error: Empty clipboard")
        exit()
    
    # Process the content
    result = process_clipboard_content($clip)
    print("Success:", result)
    
except Exception as e:
    print(f"Error occurred: {e}")
    print("Clipboard content was:", repr($clip))
```

### Input Validation

```python
# Validate input before processing
content = $clip.strip()

if not content:
    print("Error: Clipboard is empty")
elif len(content) > 1000:
    print("Error: Content too long")
elif not content.isprintable():
    print("Error: Contains non-printable characters")
else:
    # Safe to process
    print("Processing:", content)
```

### Type-Safe Operations

```python
# Safe type conversions
def safe_int(value):
    try:
        return int(value)
    except ValueError:
        return None

def safe_float(value):
    try:
        return float(value)
    except ValueError:
        return None

# Use safe conversions
number = safe_int($clip)
if number is not None:
    print("Valid integer:", number)
else:
    print("Not a valid integer")
```

## Best Practices

### 1. Always Validate Input

```python
# Good practice
if not $clip.strip():
    print("Error: No content to process")
    exit()

# Process only if we have valid input
content = $clip.strip()
```

### 2. Use Clear Output

```python
# Make your output clear and useful
print("Input:", $clip)
print("Processed:", result)
print("Summary:", summary)
```

### 3. Handle Edge Cases

```python
# Consider edge cases
content = $clip.strip()

if len(content) == 0:
    print("Empty content")
elif len(content) == 1:
    print("Single character:", content)
else:
    print("Multiple characters:", len(content))
```

### 4. Use Descriptive Variable Names

```python
# Good naming
clipboard_text = $clip
word_list = clipboard_text.split()
word_count = len(word_list)

# Avoid
t = $clip
l = t.split()
c = len(l)
```

### 5. Structure Your Code

```python
# Clear structure
def process_text(text):
    """Process the given text and return result"""
    return text.upper()

def validate_input(text):
    """Validate input text"""
    return bool(text.strip())

# Main execution
if validate_input($clip):
    result = process_text($clip)
    print("Result:", result)
else:
    print("Invalid input")
```

## Troubleshooting

### Common Issues

**Issue: Script doesn't work**
```python
# Debug by printing the clipboard content
print("Debug - Clipboard content:", repr($clip))
print("Debug - Length:", len($clip))
print("Debug - Type:", type($clip))
```

**Issue: Unexpected results**
```python
# Check what you're actually processing
print("Raw content:", repr($clip))
print("Stripped content:", repr($clip.strip()))
print("Split result:", $clip.split())
```

**Issue: Empty results**
```python
# Verify clipboard has content
if not $clip.strip():
    print("Warning: Clipboard appears to be empty")
else:
    print("Processing:", $clip)
```

### Debugging Tips

1. **Use `repr()`** to see exact content including whitespace
2. **Print intermediate steps** to understand what's happening
3. **Check data types** with `type()` function
4. **Validate assumptions** about your input data
5. **Use try/except** to catch and handle errors gracefully

### Performance Tips

1. **Avoid unnecessary operations** on large clipboard content
2. **Use efficient string operations** when possible
3. **Limit output size** to avoid overwhelming the console
4. **Process data in chunks** for very large content

## Advanced Examples

### Data Transformation Pipeline

```python
# Multi-step data transformation
import re

# Step 1: Clean the data
cleaned = re.sub(r'\s+', ' ', $clip.strip())

# Step 2: Split into items
items = [item.strip() for item in cleaned.split(',')]

# Step 3: Filter and transform
processed = [item.upper() for item in items if item]

# Step 4: Generate output
result = f"Processed {len(processed)} items: {', '.join(processed)}"
print(result)
```

### Template Processing

```python
# Process template-like content
template = $clip

# Replace placeholders
processed = template.replace('{name}', 'John')
processed = processed.replace('{date}', '2024-01-01')
processed = processed.replace('{company}', 'Acme Corp')

print("Processed template:")
print(processed)
```

### Data Analysis

```python
# Simple data analysis
lines = $clip.splitlines()
words = $clip.split()

print("Analysis Results:")
print(f"Lines: {len(lines)}")
print(f"Words: {len(words)}")
print(f"Characters: {len($clip)}")
print(f"Average words per line: {len(words) / len(lines) if lines else 0:.2f}")
```

This guide should help you write effective Python scripts for the ClipDexter application. Remember to experiment and adapt these patterns to your specific needs! 