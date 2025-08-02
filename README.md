This is a Kotlin Multiplatform project targeting Desktop (JVM).

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

## Running JVM Tests

This project includes comprehensive JVM tests to ensure functionality works correctly. The tests are located in the `composeApp/src/jvmTest` directory.

### Running All JVM Tests

To run all JVM tests:

```bash
./gradlew jvmTest
```

### Running Tests with Verbose Output

For more detailed test execution information:

```bash
./gradlew jvmTest --console=verbose
```

### Running Tests After Clean Build

To ensure tests run from a clean state:

```bash
./gradlew clean jvmTest
```

### Available Test Tasks

- `jvmTest` - Runs all JVM tests
- `allTests` - Runs tests for all targets and creates aggregated report
- `check` - Runs all checks including tests

### Test Reports

After running tests, you can find test reports in:
- **XML reports**: `composeApp/build/test-results/jvmTest/`
- **HTML reports**: `composeApp/build/reports/tests/jvmTest/index.html`

### Current Test Coverage

The project includes tests for:
- **Mustache Template Engine**: Tests for template compilation, variable substitution, conditional rendering, list iteration, nested objects, HTML escaping, and edge cases
- **Compose Desktop Application**: Basic application functionality tests

### Test Dependencies

The project uses the following testing dependencies:
- `kotlin-test` - Kotlin testing framework
- `mustache` - Mustache template engine for template-related tests

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…