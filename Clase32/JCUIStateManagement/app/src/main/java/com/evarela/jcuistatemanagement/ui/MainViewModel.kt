package com.evarela.jcuistatemanagement.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evarela.jcuistatemanagement.ui.state.MainUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow<MainUIState>(MainUIState.Success("Ready"))
    val uiState: StateFlow<MainUIState> = _uiState



    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = MainUIState.Loading
                delay(3000) // Simulating network delay
                _uiState.value = MainUIState
                    .Success("Data fetched successfully")
            } catch (e: Exception) {
                _uiState.value = MainUIState.Error("Error: $e")
            }
        }
    }
}