package com.evarela.apirestlogin3.ui.home

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.evarela.apirestlogin.ui.component.UserCard
import com.evarela.apirestlogin3.model.userModelList

@Composable
fun MainScreen(
    viewModel : MainViewModel
){
    LaunchedEffect(Unit){
        viewModel.getUserList(2)
    }
    val userList = viewModel.userList.collectAsState()
    LazyColumn (modifier = Modifier.fillMaxSize() ){
        items (userList.value){ user ->
            UserCard(user)
        }
    }
}