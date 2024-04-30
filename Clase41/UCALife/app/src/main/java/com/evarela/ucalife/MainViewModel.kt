package com.evarela.ucalife

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.evarela.ucalife.model.PostDataModel
import com.evarela.ucalife.model.PostList
import com.evarela.ucalife.repository.DataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val ViewModelContext = getApplication<Application>().applicationContext

    //update post states
    private val _uiState = MutableStateFlow<PostUpdate>(PostUpdate.Success)
    val uiState: StateFlow<PostUpdate> = _uiState
    //Signout states
    private val _signOutState = MutableStateFlow<Boolean>(false)
    val signOutState: StateFlow<Boolean> = _signOutState

    // Datastore
    val datastore = DataStore(ViewModelContext)

    fun UpdatePostData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if(_uiState.value != PostUpdate.Loading){
                    _uiState.value = PostUpdate.Loading
                    delay(2000) // simulating data fetch
                    Log.i("MainViewModel", "data fetched! updatint postList...")
                    PostList.add(
                        PostDataModel(
                            3,
                            "New post",
                            R.drawable.uca_landscape,
                            5,
                            "01/01/2024",
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                        ),
                    )
                    _uiState.value = PostUpdate.Success
                }

            } catch (e: Exception) {
                _uiState.value = PostUpdate.Error("Error: $e")
            }
        }
    }

    /*
    * Datastore functions
    * */
    fun setRememberMe(value: Boolean){
        viewModelScope.launch (Dispatchers.IO){
            datastore.saveRememberMe(value)
            _signOutState.value = true
        }
    }
}

sealed class PostUpdate {
    object Success : PostUpdate()
    data class Error(val message: String) : PostUpdate()
    object Loading : PostUpdate()
}