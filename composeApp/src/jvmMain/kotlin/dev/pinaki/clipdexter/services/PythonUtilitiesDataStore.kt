package dev.pinaki.clipdexter.services

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@Serializable
data class UtilitiesData(
    val isSetup: Boolean = false,
    val utilities: Map<String, String> = emptyMap()
)

class PythonUtilitiesDataStore {
    
    private val dataFile = File(System.getProperty("user.home"), ".clipdexter/utilities.json")
    private val dataDir = dataFile.parentFile
    
    init {
        // Ensure directory exists
        dataDir?.mkdirs()
    }
    
    private fun loadData(): UtilitiesData {
        return try {
            if (dataFile.exists()) {
                val jsonString = dataFile.readText()
                Json.decodeFromString<UtilitiesData>(jsonString)
            } else {
                UtilitiesData()
            }
        } catch (e: Exception) {
            UtilitiesData()
        }
    }
    
    private suspend fun saveData(data: UtilitiesData) {
        try {
            val jsonString = Json.encodeToString(data)
            dataFile.writeText(jsonString)
        } catch (e: Exception) {
            // Handle error
        }
    }
    
    val isSetup: Flow<Boolean> = flow {
        emit(loadData().isSetup)
    }
    
    suspend fun markAsSetup() {
        val currentData = loadData()
        saveData(currentData.copy(isSetup = true))
    }
    
    suspend fun saveUtility(id: String, pythonCode: String) {
        val currentData = loadData()
        val updatedUtilities = currentData.utilities.toMutableMap()
        updatedUtilities[id] = pythonCode
        saveData(currentData.copy(utilities = updatedUtilities))
    }
    
    suspend fun getUtility(id: String): String? {
        return loadData().utilities[id]
    }
    
    suspend fun getAllUtilities(): Map<String, String> {
        return loadData().utilities
    }
    
    suspend fun deleteUtility(id: String) {
        val currentData = loadData()
        val updatedUtilities = currentData.utilities.toMutableMap()
        updatedUtilities.remove(id)
        saveData(currentData.copy(utilities = updatedUtilities))
    }
    
    suspend fun clearAllUtilities() {
        val currentData = loadData()
        saveData(currentData.copy(utilities = emptyMap()))
    }
} 