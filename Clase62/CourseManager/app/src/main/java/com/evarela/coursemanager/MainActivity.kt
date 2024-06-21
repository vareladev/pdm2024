package com.evarela.coursemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.evarela.coursemanager.ui.navigation.Navigation
import com.evarela.coursemanager.ui.theme.CourseManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // view model
        val viewModel : MainViewModel by viewModels()

        setContent {
            val navController = rememberNavController()

            CourseManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Navigation(viewModel, navController)
                }
            }
        }
    }
}

