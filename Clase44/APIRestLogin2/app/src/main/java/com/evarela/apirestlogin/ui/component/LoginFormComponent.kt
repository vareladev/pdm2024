

package com.evarela.apirestlogin.ui.component

import android.content.Context
import android.util.Log
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
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.evarela.apirestlogin.MainViewModel
import com.evarela.apirestlogin.R
import com.evarela.apirestlogin.UiState
import com.evarela.apirestlogin.data.LoginData
import com.evarela.apirestlogin.ui.navigation.ScreenRoute
import com.evarela.apirestlogin.ui.screen.HomeScreen
import com.evarela.apirestlogin.ui.theme.White
import com.evarela.ucalife.component.login.LoginField
import com.evarela.ucalife.component.login.PasswordField

@Composable
fun LoginFormComponent(
    navController: NavController,
    viewModel : MainViewModel
){
    var loginData by remember { mutableStateOf(LoginData()) }

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
        navController.navigate(route = ScreenRoute.Home.route)
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
            value = loginData.login,
            onChange = { data -> loginData = loginData.copy(login = data) },
            modifier = Modifier.fillMaxWidth()
        )
        PasswordField(
            value = loginData.pwd,
            onChange = { data -> loginData = loginData.copy(pwd = data) },
            submit = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.checkLogin(loginData)
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