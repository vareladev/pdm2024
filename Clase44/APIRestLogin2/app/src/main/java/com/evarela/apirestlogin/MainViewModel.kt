package com.evarela.apirestlogin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evarela.apirestlogin.data.LoginData
import com.evarela.apirestlogin.data.api.ApiClient
import com.evarela.apirestlogin.data.userDataList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    val uiState: StateFlow<UiState> = _uiState

    fun setStateToReady (){
        _uiState.value = UiState.Ready
    }

    fun checkLogin(loginData : LoginData){
        viewModelScope.launch (Dispatchers.IO){
            try{
                _uiState.value = UiState.Loading
                val result = ApiClient.apiService.login(loginData)
                Log.d("viewModel", result.toString())
                _uiState.value = UiState.Success(result.token)
            }
            catch (e : HttpException){
                Log.d("viewModel", "ERROR! $e")
                _uiState.value = UiState.Error(400)
            }
        }
    }

    fun getUserList(pageId: Int){
        viewModelScope.launch (Dispatchers.Default){
            try{
                val result = ApiClient.apiService.getUserList(pageId)
                Log.d("viewModel", result.data.toString())
                for(user in result.data){
                    userDataList.add(user)
                }
                Log.d("viewModel", userDataList.toString())
            }
            catch (e: Exception){
                Log.d("viewModel", "ERROR! $e")
            }
            catch (e : HttpException){
                Log.d("viewModel", "ERROR! $e")
            }
        }
    }
}

sealed class UiState {
    data class Success(val token: String): UiState()
    data class Error(val code: Int) : UiState()
    object Ready : UiState()
    object Loading : UiState()
}