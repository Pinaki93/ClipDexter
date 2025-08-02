# Koin Setup in ClipDexter

This project uses Koin for dependency injection in the Compose Multiplatform application.

## Setup

### 1. Dependencies
The following Koin dependencies are included in `gradle/libs.versions.toml`:
```toml
koin = "3.5.3"
koin-core = { module = "io.insert-koin:koin-core", version.ref = "koin" }
```

### 2. Koin Initialization
Koin is initialized in `main.kt`:
```kotlin
fun main() = application {
    // Initialize Koin
    KoinHelper.initKoin()
    
    Window(
        onCloseRequest = ::exitApplication,
        title = "ClipDexter",
    ) {
        App()
    }
}
```

### 3. Module Definition
Dependencies are defined in `di/AppModule.kt`:
```kotlin
val appModule = module {
    // Singleton dependencies
    single { PythonExecutor() }
    single<ClipboardService> { ClipboardServiceImpl() }
    
    // ViewModels
    factory { MainViewModel(get()) }
}
```

## Usage

### Getting Dependencies in Composables
```kotlin
@Composable
fun MyComposable() {
    val clipboardService: ClipboardService = remember { GlobalContext.get().get() }
    // Use clipboardService...
}
```

### Getting Dependencies in Regular Classes
```kotlin
class MyClass {
    private val clipboardService: ClipboardService = GlobalContext.get().get()
    // Use clipboardService...
}
```

### Using ViewModels
```kotlin
@Composable
fun MyComposable() {
    val viewModel: MainViewModel = remember { GlobalContext.get().get() }
    // Use viewModel...
}
```

## Available Dependencies

1. **PythonExecutor** - Singleton for executing Python code
2. **ClipboardService** - Interface for clipboard operations
3. **MainViewModel** - ViewModel for UI state management

## Adding New Dependencies

1. Create your service/class
2. Add it to `di/AppModule.kt`:
   ```kotlin
   single { YourService() }  // Singleton
   factory { YourClass() }   // New instance each time
   ```
3. Inject it where needed using `GlobalContext.get().get()`

## Notes

- This setup uses Koin Core only (no Android-specific dependencies)
- For Compose Multiplatform, we use manual injection instead of `koinViewModel()`
- All dependencies are available across the entire application 