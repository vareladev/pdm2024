package com.evarela.jcuistatemanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evarela.jcuistatemanagement.ui.MainViewModel
import com.evarela.jcuistatemanagement.ui.state.MainUIState
import com.evarela.jcuistatemanagement.ui.theme.JCUIStateManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val mainViewModel = MainViewModel()


        super.onCreate(savedInstanceState)
        setContent {
            JCUIStateManagementTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyScreen(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun MyScreen(viewModel: MainViewModel) {
    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState.value) {
            is MainUIState.Error -> {
                Text(text =
                (uiState.value as MainUIState.Error).message)
            }
            MainUIState.Loading -> {
                CircularProgressIndicator()
            }
            is MainUIState.Success -> {
                Text(text = (uiState.value as MainUIState.Success).data)
            }
        }

        // Button to trigger data fetch
        Spacer(modifier = Modifier.height(64.dp))
        Button(onClick = { viewModel.fetchData() }) {
            Text(text = "Fetch Data")
        }
    }
}
