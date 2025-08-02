package dev.pinaki.clipdexter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.pinaki.clipdexter.PyUtilitiesRepository
import dev.pinaki.clipdexter.PythonExecutor
import dev.pinaki.clipdexter.UtilityItem
import dev.pinaki.clipdexter.services.ClipboardService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UtilitiesViewModel(
    private val utilitiesRepository: PyUtilitiesRepository,
    private val pythonExecutor: PythonExecutor,
    private val clipboardService: ClipboardService
) : ViewModel() {
    
    private val _utilities = MutableStateFlow<List<UtilityItem>>(emptyList())
    val utilities: StateFlow<List<UtilityItem>> = _utilities.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _isSetup = MutableStateFlow(false)
    val isSetup: StateFlow<Boolean> = _isSetup.asStateFlow()
    
    private val _currentPythonCode = MutableStateFlow("")
    val currentPythonCode: StateFlow<String> = _currentPythonCode.asStateFlow()
    
    private val _currentUtilityId = MutableStateFlow("")
    val currentUtilityId: StateFlow<String> = _currentUtilityId.asStateFlow()
    
    init {
        setupIfNeeded()
        observeSetupStatus()
    }
    
    private fun setupIfNeeded() {
        viewModelScope.launch {
            utilitiesRepository.setupInitialData()
        }
    }
    
    private fun observeSetupStatus() {
        viewModelScope.launch {
            utilitiesRepository.isSetup.collect { setup ->
                _isSetup.value = setup
                if (setup) {
                    loadUtilities()
                }
            }
        }
    }
    
    private fun loadUtilities() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val utilitiesList = utilitiesRepository.getUtilities()
                _utilities.value = utilitiesList
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun runUtility(utilityId: String) {
        viewModelScope.launch {
            try {
                val pythonCode = utilitiesRepository.getPythonCode(utilityId)
                if (pythonCode != null) {
                    val clipboardText = clipboardService.getClipboardText()
                    val result = pythonExecutor.executePythonScript(pythonCode, clipboardText)
                    clipboardService.copyToClipboard(result)
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    fun loadUtilityForEditing(utilityId: String) {
        viewModelScope.launch {
            try {
                val pythonCode = utilitiesRepository.getPythonCode(utilityId)
                _currentPythonCode.value = pythonCode ?: ""
                _currentUtilityId.value = utilityId
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    fun updatePythonCode(code: String) {
        _currentPythonCode.value = code
    }
    
    fun saveCurrentUtility() {
        viewModelScope.launch {
            try {
                val utilityId = _currentUtilityId.value
                val pythonCode = _currentPythonCode.value
                if (utilityId.isNotEmpty() && pythonCode.isNotEmpty()) {
                    utilitiesRepository.saveUtility(utilityId, "", pythonCode)
                    loadUtilities() // Refresh the list
                }
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    fun clearCurrentUtility() {
        _currentPythonCode.value = ""
        _currentUtilityId.value = ""
    }
} 