package com.evarela.ucalife.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evarela.ucalife.MainViewModel
import com.evarela.ucalife.component.post.ProfileComponent
import com.evarela.ucalife.component.post.ProfilePic
import com.evarela.ucalife.model.UserDataModel

@Composable
fun ProfileScreen(
    innerPadding: PaddingValues,
    viewModel : MainViewModel
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ProfileComponent(
            UserDataModel(
                "Username",
                "user@email.com",
                24
            ),
            viewModel
        )
    }
}

