package dev.pinaki.clipdexter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.pinaki.clipdexter.ui.screens.DetailScreen
import dev.pinaki.clipdexter.ui.screens.ListScreen
import dev.pinaki.clipdexter.viewmodels.UtilitiesViewModel

sealed class Screen {
    object List : Screen()
    data class Detail(val itemId: String) : Screen()
}

class NavigationState {
    var currentScreen by mutableStateOf<Screen>(Screen.List)
        private set
    
    fun navigateToDetail(itemId: String) {
        currentScreen = Screen.Detail(itemId)
    }
    
    fun navigateBack() {
        currentScreen = Screen.List
    }
}

@Composable
fun Navigation(utilitiesViewModel: UtilitiesViewModel) {
    val navigationState = remember { NavigationState() }
    
    when (val screen = navigationState.currentScreen) {
        is Screen.List -> {
            ListScreen(
                utilitiesViewModel = utilitiesViewModel,
                onItemClick = { itemId ->
                    navigationState.navigateToDetail(itemId)
                }
            )
        }
        is Screen.Detail -> {
            DetailScreen(
                itemId = screen.itemId,
                utilitiesViewModel = utilitiesViewModel,
                onBackClick = {
                    navigationState.navigateBack()
                }
            )
        }
    }
} 