# ClipDexter - Python Utilities Management System

ClipDexter is a Kotlin Multiplatform desktop application that provides a comprehensive Python utilities management system. It allows users to run Python scripts on clipboard data and manage a collection of text processing utilities.

## Features

### 🐍 Python Utilities Management
- **20 Pre-built Utilities**: Ready-to-use Python scripts for common text processing tasks
- **Customizable**: Edit and save your own Python utilities
- **Persistent Storage**: All utilities are saved to a local data store
- **Clipboard Integration**: Execute utilities directly on clipboard content

### 📋 Available Utilities

#### Text Transformation
- **Convert to Uppercase** - Transform text to uppercase
- **Convert to Lowercase** - Transform text to lowercase  
- **Capitalize Each Word** - Capitalize first letter of each word
- **Trim Extra Whitespace** - Remove extra spaces and normalize whitespace
- **Remove All Line Breaks** - Convert multi-line text to single line
- **Convert Tabs to Spaces** - Replace tab characters with spaces

#### Data Extraction
- **Extract First URL** - Find the first URL in text
- **Extract All URLs** - Find all URLs in text
- **Extract All Email Addresses** - Find all email addresses
- **Extract All Numbers** - Find all numbers (integers and decimals)

#### JSON & CSV Processing
- **Convert CSV to JSON** - Transform CSV data to JSON format
- **Pretty-print JSON** - Format JSON with proper indentation
- **Minify JSON** - Remove all whitespace from JSON

#### Encoding & Decoding
- **Encode to Base64** - Encode text to base64
- **Decode from Base64** - Decode base64 text

#### Analysis & Counting
- **Count Words and Characters** - Provide character, word, and line counts

#### HTML Processing
- **Remove All HTML Tags** - Strip HTML tags from text
- **Escape for HTML** - Escape special characters for HTML

#### Text Organization
- **Sort Lines Alphabetically** - Sort lines in alphabetical order
- **Deduplicate Lines** - Remove duplicate lines while preserving order

### 🎨 Modern UI
- **Material 3 Design** - Beautiful and intuitive interface
- **Real-time Updates** - Instant feedback on operations
- **Responsive Layout** - Adapts to different screen sizes

## Installation & Setup

### Prerequisites
- **Java 11+** - Required for Kotlin Multiplatform
- **Python 3** - Required for executing Python utilities
- **Gradle** - Build system (included in project)

### Running the Application

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd ClipDexter
   ```

2. **Run the application**:
   ```bash
   ./gradlew run
   ```

3. **First-time setup**:
   - The application will automatically create the data store on first run
   - Initial utilities will be loaded into `~/.clipdexter/utilities.json`

### Building for Distribution

```bash
# Build for macOS
./gradlew packageDmg

# Build for Windows
./gradlew packageMsi

# Build for Linux
./gradlew packageDeb
```

## Usage

### Running Utilities

1. **Copy text to clipboard** that you want to process
2. **Open ClipDexter** and browse the list of utilities
3. **Click "Run"** on any utility to process your clipboard content
4. **Result is automatically copied** back to your clipboard

### Editing Utilities

1. **Click "Edit"** on any utility in the list
2. **Modify the Python code** in the editor
3. **Click "Save Changes"** to update the utility
4. **Changes are persisted** to the data store

### Data Storage

- **Location**: `~/.clipdexter/utilities.json`
- **Format**: JSON with utilities and setup status
- **Backup**: You can backup this file to preserve your custom utilities

## Development

### Project Structure

```
composeApp/src/jvmMain/kotlin/dev/pinaki/clipdexter/
├── App.kt                           # Main application entry point
├── PyUtilitiesRepository.kt         # Repository for managing utilities
├── PythonExecutor.kt               # Python script execution engine
├── ClipboardUtil.kt                # Clipboard operations
├── di/
│   └── AppModule.kt                # Dependency injection setup
├── navigation/
│   └── Navigation.kt               # Screen navigation
├── services/
│   └── PythonUtilitiesDataStore.kt # Data persistence layer
├── ui/screens/
│   ├── ListScreen.kt               # Utilities list view
│   └── DetailScreen.kt             # Utility editor
└── viewmodels/
    └── UtilitiesViewModel.kt       # UI state management
```

### Key Technologies

- **Kotlin Multiplatform** - Cross-platform development
- **Compose Multiplatform** - Modern UI framework
- **Koin** - Dependency injection
- **Kotlinx Serialization** - JSON serialization
- **Kotlinx Coroutines** - Asynchronous programming

### Adding New Utilities

1. **Edit `PyUtilitiesRepository.kt`**
2. **Add utility to `getInitialUtilities()`** map
3. **Provide unique ID** and Python code
4. **Update `getUtilityName()`** method with display name

### Testing

```bash
# Run all tests
./gradlew test

# Run JVM tests only
./gradlew jvmTest

# Run with verbose output
./gradlew test --console=verbose
```

## Configuration

### Python Path
The application uses `python3` command. If your system uses a different command, update `PythonExecutor.kt`:

```kotlin
val processBuilder = ProcessBuilder("python3", tempFile.absolutePath)
```

### Data Store Location
To change the data store location, modify `PythonUtilitiesDataStore.kt`:

```kotlin
private val dataFile = File(System.getProperty("user.home"), ".clipdexter/utilities.json")
```

## Troubleshooting

### Common Issues

1. **Python not found**: Ensure Python 3 is installed and accessible via `python3` command
2. **Permission errors**: Check write permissions for `~/.clipdexter/` directory
3. **Build failures**: Ensure Java 11+ is installed and JAVA_HOME is set correctly

### Debug Mode

Run with debug logging:
```bash
./gradlew run --debug
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

---

**Built with ❤️ using Kotlin Multiplatform and Compose**