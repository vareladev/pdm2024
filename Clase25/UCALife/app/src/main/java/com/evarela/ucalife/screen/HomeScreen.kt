package com.evarela.ucalife.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.evarela.ucalife.R
import com.evarela.ucalife.component.post.Post
import com.evarela.ucalife.model.PostDataModel
import com.evarela.ucalife.model.PostList

@Composable
fun HomeScreen(innerPadding: PaddingValues){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        items(PostList) { postItem ->
            Post(postItem)
        }
    }
}


