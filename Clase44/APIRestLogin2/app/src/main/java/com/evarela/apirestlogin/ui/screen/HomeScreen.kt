package com.evarela.apirestlogin.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.evarela.apirestlogin.MainViewModel
import com.evarela.apirestlogin.data.api.UserData
import com.evarela.apirestlogin.data.userDataList
import com.evarela.apirestlogin.ui.component.UserCard

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel : MainViewModel
){
    LaunchedEffect(Unit){
        Log.i("HomeScreen", "Se ejecuta LaunchedEffect")
        viewModel.getUserList(2)
    }
    LazyColumn (modifier = Modifier.fillMaxSize() ){
        items(userDataList){ user ->
            UserCard(user)
        }
    }
}