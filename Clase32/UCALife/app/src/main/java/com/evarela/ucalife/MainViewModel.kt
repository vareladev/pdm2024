package com.evarela.ucalife

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evarela.ucalife.model.PostDataModel
import com.evarela.ucalife.model.PostList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<PostUpdate>(PostUpdate.Success)
    val uiState: StateFlow<PostUpdate> = _uiState

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
}

sealed class PostUpdate {
    object Success : PostUpdate()
    data class Error(val message: String) : PostUpdate()
    object Loading : PostUpdate()
}