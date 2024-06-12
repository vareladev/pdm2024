package com.evarela.apirestlogin3.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evarela.apirestlogin3.domain.LoginUseCase
import com.evarela.apirestlogin3.domain.getUserListUseCase
import com.evarela.apirestlogin3.domain.model.LoginModel
import com.evarela.apirestlogin3.domain.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserListUseCase: getUserListUseCase,
    private val loginUseCase: LoginUseCase,
): ViewModel() {


    private val _userList = MutableStateFlow(listOf<UserModel>())
    val userList = _userList.asStateFlow()

    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    val uiState: StateFlow<UiState> = _uiState

    fun setStateToReady (){
        _uiState.value = UiState.Ready
    }

    fun checkLogin(loginModel : LoginModel){
        viewModelScope.launch {
            try{
                _uiState.value = UiState.Loading
                val token = loginUseCase.invoke(loginModel)
                _uiState.value = UiState.Success(token)
            }
            catch (e : HttpException){
                Log.d("viewModel", "ERROR! $e")
                _uiState.value = UiState.Error(400)
            }
        }
    }

    fun getUserList(pageId: Int){
        viewModelScope.launch{
            _userList.value = getUserListUseCase.invoke(pageId)
        }
    }
}

sealed class UiState {
    data class Success(val token: String): UiState()
    data class Error(val code: Int) : UiState()
    data object Ready : UiState()
    data object Loading : UiState()
}