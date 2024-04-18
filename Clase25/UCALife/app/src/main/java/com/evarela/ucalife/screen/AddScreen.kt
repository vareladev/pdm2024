package com.evarela.ucalife.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evarela.ucalife.component.post.AddPostComponent

@Composable
fun AddScreen(innerPadding: PaddingValues){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AddPostComponent()
    }
}

@Preview
@Composable
fun PreviewAddScree(){
    AddScreen(innerPadding = PaddingValues(0.dp))
}