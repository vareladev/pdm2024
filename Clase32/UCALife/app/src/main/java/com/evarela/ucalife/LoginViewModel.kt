package com.evarela.ucalife

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evarela.ucalife.model.LoginData
import com.evarela.ucalife.model.PostDataModel
import com.evarela.ucalife.model.PostList
import com.evarela.ucalife.util.ErrorList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel: ViewModel()  {

    var loginData by mutableStateOf(LoginData())

    private val _loginState = MutableStateFlow<LoginButtonState>(LoginButtonState.Ready)
    val loginState: StateFlow<LoginButtonState> = _loginState

    fun checkLogin(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loginState.value = LoginButtonState.Loading
                delay(2000) // simulating login data fetch
                if ( checkCredentials(loginData, context))
                    _loginState.value = LoginButtonState.Success
                else{
                    _loginState.value = LoginButtonState.Error(1)
                    loginData = LoginData()
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, ErrorList[(_loginState.value as LoginButtonState.Error).errorCode], Toast.LENGTH_SHORT).show()
                    }
                    _loginState.value = LoginButtonState.Ready
                }

            } catch (e: Exception) {
                _loginState.value = LoginButtonState.Error(2)
            }
        }
    }


}



sealed class LoginButtonState {
    object Ready : LoginButtonState()
    object Success : LoginButtonState()
    data class Error(val errorCode: Int) : LoginButtonState()
    object Loading : LoginButtonState()
}

fun checkCredentials(loginData: LoginData, context: Context): Boolean {
    return if (loginData.isNotEmpty() && loginData.login == "admin") {
        context.startActivity(Intent(context, MainActivity::class.java))
        (context as Activity).finish()
        true
    } else false
}