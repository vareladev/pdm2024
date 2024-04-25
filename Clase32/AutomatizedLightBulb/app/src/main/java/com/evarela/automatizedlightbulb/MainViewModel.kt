package com.evarela.automatizedlightbulb

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class MainViewModel: ViewModel() {

    private var stateValue = true
    val switchControllerFlow = flow {
        while (true) {
            stateValue = !stateValue
            //Log.i("MainViewModel","new value: $stateValue")
            emit(stateValue)
            delay(3000)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        true
    )
}