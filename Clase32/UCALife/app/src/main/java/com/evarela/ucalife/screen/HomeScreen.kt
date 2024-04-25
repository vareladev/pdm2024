package com.evarela.ucalife.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.evarela.ucalife.MainViewModel
import com.evarela.ucalife.PostUpdate
import com.evarela.ucalife.R
import com.evarela.ucalife.component.post.Post
import com.evarela.ucalife.model.PostDataModel
import com.evarela.ucalife.model.PostList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.material3.CircularProgressIndicator as CircularProgressIndicator1

@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    viewModel: MainViewModel
){
    val uiState = viewModel.uiState.collectAsState()

    LazyColumn(
        //state = lazyListState,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        items(PostList) { postItem ->
            Post(postItem)
        }
        if(uiState.value == PostUpdate.Loading){
            item {
                Box (
                    modifier = Modifier
                        .fillMaxWidth(),
                    Alignment.Center
                ){
                    CircularProgressIndicator1()
                }

            }
        }
        item {
            LaunchedEffect(true) {
                viewModel.UpdatePostData()
            }
        }
    }

}


