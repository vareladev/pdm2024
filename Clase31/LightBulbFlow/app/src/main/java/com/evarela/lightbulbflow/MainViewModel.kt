package com.evarela.lightbulbflow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    var flowProducer: Flow<Boolean> = flow {
        var switchValue : Boolean = true
        while (true){
            switchValue = !switchValue
            emit(switchValue)
            Thread.sleep(3000)
        }
    }
    // ********************* Ejemplo flows *******************
    fun flowListener(){
        viewModelScope.launch(Dispatchers.Default) {
            flowProducer.collect{
                    Log.i("viewmodeltest", "Valor del estado: $it")
                }
        }
    }
}