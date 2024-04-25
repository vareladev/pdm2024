package com.evarela.lightbulbstateflow

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

    /*var flowCountProducer: Flow<Int> = flow {
        var counter : Int = 0
        while (true){
            counter += 1
            emit(counter)
            Thread.sleep(3000)
        }
    }*/




    var flowProducer: Flow<Boolean> = flow {
        var switchValue : Boolean = true
        while (true){
            switchValue = !switchValue
            emit(switchValue)
            delay(3000)
        }
    }

    // ********************* Ejemplo stateflow *******************

    private val _flowState = MutableStateFlow<Boolean>(true)
    val flowState: StateFlow<Boolean> = _flowState




    fun flowConsumer(){
        viewModelScope.launch(Dispatchers.Default) {
            flowProducer
                .collect{
                    newValue ->
                    _flowState.value = newValue
                    //Log.i("viewmodel", "Valor del estado: $it")
                }
        }
    }
}