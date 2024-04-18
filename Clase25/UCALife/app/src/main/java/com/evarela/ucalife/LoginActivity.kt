package com.evarela.ucalife

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import com.evarela.ucalife.component.login.LoginFormComponent
import com.evarela.ucalife.ui.theme.UCALifeTheme

class LoginActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent{
            UCALifeTheme {
                Surface {
                    val context = LocalContext.current
                    LoginFormComponent(context)
                }
            }

        }
    }
}

