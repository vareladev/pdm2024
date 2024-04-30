package com.evarela.ucalife

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.evarela.ucalife.model.LoginData
import com.evarela.ucalife.repository.DataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) :
    AndroidViewModel(application)  {

    private val viewModelContext = getApplication<Application>()
                                        .applicationContext

    // Datastore
    val datastore = DataStore(viewModelContext)

    //Estado que almacena la información de login: usuario, contraseña y opción "remember me"
    var loginData by mutableStateOf(LoginData())
    // Estados que gestionan el estado de la interfaz
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Ready)
    val loginState: StateFlow<LoginState> = _loginState


    fun checkLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Coloca la pantalla en estado "loading" el boton se deshabilita y aparece un progressbar circular
                _loginState.value = LoginState.Loading
                // Simula una consulta a base de datos o API que dura 2 segundos
                delay(2000)
                // Valida los datos de login. En este momento esta "quemado" solo pregunta si el usuario es igual a "admin"
                if (checkCredentials(loginData)){
                    // Si las credenciales son correctas. Se almacena la opcion "recuerdame en este dispositivo" en datastore
                    setRememberMe(loginData.remember)
                    // El estado de la interfaz cambian a "Success" que habilita al LoginActivity para cambiar al "MainActivity"
                    _loginState.value = LoginState.Success
                }
                else{
                    // Si la validación de las credenciales falla, entonces se define un error para ser gestionado en la interfaz
                    _loginState.value = LoginState.Error(1)
                    // Se borran los datos ingresados para limpiar los campos
                    loginData = LoginData()
                }

            } catch (e: Exception) {
                _loginState.value = LoginState.Error(2)
            }
        }
    }

    // Función que permite para colocar la interfaz en estado "ready" luego de haber gestionado algun error
    fun setReadyState() {
        _loginState.value = LoginState.Ready
    }

    // función que evalua los datos ingresados.
    private fun checkCredentials(loginData: LoginData): Boolean {
        return loginData.isNotEmpty() && loginData.login == "admin"
    }

    // Datastore functions
    private fun setRememberMe(value: Boolean){
        viewModelScope.launch (Dispatchers.IO){
            datastore.saveRememberMe(value)
        }
    }
}

sealed class LoginState {
    data object Ready : LoginState()
    data object Success : LoginState()
    data class Error(val errorCode: Int) : LoginState()
    data object Loading : LoginState()
}

