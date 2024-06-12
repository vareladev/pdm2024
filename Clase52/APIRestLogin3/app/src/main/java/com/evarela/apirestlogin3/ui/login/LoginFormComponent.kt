

package com.evarela.apirestlogin3.ui.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.evarela.apirestlogin3.ui.home.MainViewModel
import com.evarela.apirestlogin3.R
import com.evarela.apirestlogin3.ui.home.UiState
import com.evarela.apirestlogin3.domain.model.LoginModel
import com.evarela.apirestlogin3.ui.navigation.ScreenRoute
import com.evarela.ucalife.component.login.PasswordField

@Composable
fun LoginFormComponent(
    navController: NavController,
    viewModel : MainViewModel
){
    var loginModel by remember { mutableStateOf(LoginModel()) }

    //MainActivity context
    val context = LocalContext.current
    
    // Estado de la interfaz
    val uiState = viewModel.uiState.collectAsState()

    if(uiState.value is UiState.Error){
        when((uiState.value as UiState.Error).code) {
            400 -> {
                showMessage(context,"Error: usuario o contraseña incorrecta")
            }
        }
        viewModel.setStateToReady()
    }

    if(uiState.value is UiState.Success){
        showMessage(context,"Token: ${(uiState.value as UiState.Success).token}")
        navController.navigate(route = ScreenRoute.Main.route)
        viewModel.setStateToReady()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        if(uiState.value == UiState.Loading){
            ProgressDialog()
        }
        LoginField(
            value = loginModel.login,
            onChange = { data -> loginModel = loginModel.copy(login = data) },
            modifier = Modifier.fillMaxWidth()
        )
        PasswordField(
            value = loginModel.pwd,
            onChange = { data -> loginModel = loginModel.copy(pwd = data) },
            submit = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.checkLogin(loginModel)
            },
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.login_btn_login))
        }
    }
}

fun showMessage(
    context: Context,
    msg: String
){
    Toast.makeText(context,
        msg,
        Toast.LENGTH_SHORT).show()
}

@Composable
fun ProgressDialog(){
    Dialog(
        onDismissRequest = { },
        DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Box(
            contentAlignment= Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .background(Color.Transparent, shape = RoundedCornerShape(8.dp))
        ) {
            CircularProgressIndicator()
        }
    }
}