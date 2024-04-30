package com.evarela.ucalife

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.evarela.ucalife.component.login.LoginFormComponent
import com.evarela.ucalife.ui.theme.UCALifeTheme
import com.evarela.ucalife.util.changeActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)


        // Habitando splashscreen
        installSplashScreen()

        // Inicializando ViewModel
        val viewModel = ViewModelProvider(this)
            .get(LoginViewModel::class.java)

        // Verificando la opción "recuerdame" seleccionada previamente por el usuario.
        lifecycleScope.launch (Dispatchers.IO){
            viewModel.datastore.getRememberMe().collect {
                response ->
                if (response == true){
                    changeActivity(this@LoginActivity, MainActivity::class.java)
                }
            }
        }

        setContent{
            UCALifeTheme {
                Surface {
                    LoginFormComponent(viewModel)
                }
            }

        }
    }


}

