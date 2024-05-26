package com.evarela.apirestlogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.evarela.apirestlogin.ui.component.LoginFormComponent
import com.evarela.apirestlogin.ui.navigation.Navigation

import com.evarela.apirestlogin.ui.theme.APIRestLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // view model
        val viewModel: MainViewModel by viewModels()

        setContent {
            APIRestLoginTheme {
                Surface {
                    Navigation(viewModel)
                }
            }
        }
    }
}

