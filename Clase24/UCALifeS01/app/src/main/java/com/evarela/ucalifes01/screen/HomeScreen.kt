package com.evarela.ucalifes01.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evarela.ucalifes01.R
import com.evarela.ucalifes01.component.PostComponent
import com.evarela.ucalifes01.model.PostDataModel
import com.evarela.ucalifes01.model.PostList

@Composable
fun HomeScreen(innerPadding: PaddingValues){
    LazyColumn (
        modifier = Modifier
            .padding(innerPadding)
    ){
        items(PostList) {
            postItem -> PostComponent(postItem)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHomeScreen(){
    HomeScreen(PaddingValues(0.dp))
}