/*
* Reference: https://medium.com/@WhiteBatCodes/simple-login-page-in-jetpack-compose-9c92af690234
* */

package com.evarela.ucalife.component.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.evarela.ucalife.LoginState
import com.evarela.ucalife.LoginViewModel
import com.evarela.ucalife.MainActivity
import com.evarela.ucalife.R
import com.evarela.ucalife.util.changeActivity
import com.evarela.ucalife.util.showMessage

@Composable
fun LoginFormComponent(
    viewModel: LoginViewModel
){
    /*
    * Forma de inicializar el contexto del activity actual (LoginActivity) en un entorno Compose.
    * La idea es no tener que mandar a través de parámetros el contexto desde LoginActivity
    * El contexto se utiliza para dos tareas:
    * 1. Para mostrar mensajes (TOAST) en caso de que los datos de login esten incorrectos
    * 2. Cambiar de pantalla cuando las credenciales sean correctas
    * */
    val context = LocalContext.current

    /*
    * Estado gestionado en el viewModel a través de stateFlow. para hacer cambios en
    * la interfaz a partir de algunos eventos generados por el usuario.
    * Por ejemplo: cuando el usuario inicia sesión, el boton de login se deshabilita y aparece
    * un progressbar circular
    * */
    val loginState = viewModel.loginState.collectAsState()

    /*
    * Una de las tareas del loginState es verificar si las credenciales ingresadas son correctas.
    * En este caso, el valor de LoginState es igual a "Success". Por lo tanto, será necesario
    * cambiar al MainActivity.
    * */
    if(loginState.value == LoginState.Success){
        changeActivity(context, MainActivity::class.java)
    }
    if (loginState.value is LoginState.Error){
        showMessage(context, (loginState.value as LoginState.Error).errorCode)
        viewModel.setReadyState()
    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        Image(
            modifier = Modifier
                .size(128.dp),
            painter = painterResource(id = R.drawable.login_logo),
            contentDescription = "")
        Spacer(modifier = Modifier.padding(32.dp))
        LoginField(
            value = viewModel.loginData.login,
            onChange = { data -> viewModel.loginData = viewModel.loginData.copy(login = data) },
            modifier = Modifier.fillMaxWidth()
        )
        PasswordField(
            value = viewModel.loginData.pwd,
            onChange = { data -> viewModel.loginData = viewModel.loginData.copy(pwd = data) },
            submit = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        LabeledCheckbox(
            label = stringResource(id = R.string.login_remember_me),
            onCheckChanged = {
                viewModel.loginData = viewModel.loginData.copy(remember = !viewModel.loginData.remember)
            },
            isChecked = viewModel.loginData.remember
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.checkLogin()
            },
            enabled = (viewModel.loginData.isNotEmpty() && (loginState.value == LoginState.Ready)),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            if(loginState.value == LoginState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.
                    then(Modifier.size(32.dp)
                    ))
            }
            else{
                Text(stringResource(id = R.string.login_btn_login))
            }
        }

    }
}

@Composable
fun LabeledCheckbox(
    label: String,
    onCheckChanged: () -> Unit,
    isChecked: Boolean
) {

    Row(
        Modifier
            .clickable(
                onClick = onCheckChanged
            )
            .padding(4.dp)
    ) {
        Checkbox(checked = isChecked, onCheckedChange = null)
        Spacer(Modifier.size(6.dp))
        Text(label)
    }
}

