package dev.pinaki.clipdexter.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.pinaki.clipdexter.UtilityItem
import dev.pinaki.clipdexter.viewmodels.UtilitiesViewModel

@Composable
fun ListScreen(
    utilitiesViewModel: UtilitiesViewModel,
    onItemClick: (String) -> Unit
) {
    val utilities by utilitiesViewModel.utilities.collectAsState()
    val isLoading by utilitiesViewModel.isLoading.collectAsState()
    val isSetup by utilitiesViewModel.isSetup.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Python Utilities",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (!isSetup) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Setting up utilities...")
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(utilities) { utility ->
                    UtilityCard(
                        utility = utility,
                        onRunClick = { utilitiesViewModel.runUtility(utility.pythonCodeId) },
                        onEditClick = { 
                            utilitiesViewModel.loadUtilityForEditing(utility.pythonCodeId)
                            onItemClick(utility.pythonCodeId) 
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun UtilityCard(
    utility: UtilityItem,
    onRunClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = utility.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onRunClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Run")
                }
                
                OutlinedButton(
                    onClick = onEditClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Edit")
                }
            }
        }
    }
} 