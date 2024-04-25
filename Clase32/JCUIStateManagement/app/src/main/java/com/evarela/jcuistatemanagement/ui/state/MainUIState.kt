package com.evarela.jcuistatemanagement.ui.state

sealed class MainUIState {
    object Loading : MainUIState()
    data class Success(val data: String) : MainUIState()
    data class Error(val message: String) : MainUIState()

}