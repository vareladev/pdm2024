/*
* Reference: https://medium.com/@WhiteBatCodes/simple-login-page-in-jetpack-compose-9c92af690234
* */

package com.evarela.ucalife.component.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.evarela.ucalife.LoginButtonState
import com.evarela.ucalife.LoginViewModel
import com.evarela.ucalife.MainActivity
import com.evarela.ucalife.R
import com.evarela.ucalife.model.LoginData
import com.evarela.ucalife.util.ErrorList

@Composable
fun LoginFormComponent(
    context: Context,
    viewModel: LoginViewModel
){
    //var loginData by remember { mutableStateOf(LoginData()) }
    val loginButtonState = viewModel.loginState.collectAsState()

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
            submit = {
                if (!checkCredentials(viewModel.loginData, context)) viewModel.loginData = LoginData()
            },
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
                viewModel.checkLogin(context)
            },
            enabled = (viewModel.loginData.isNotEmpty() && (loginButtonState.value == LoginButtonState.Ready)),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            when(loginButtonState.value){
                LoginButtonState.Loading ->
                    CircularProgressIndicator(
                        modifier = Modifier.
                        then(Modifier.size(32.dp)
                        ))
                else -> Text(stringResource(id = R.string.login_btn_login))
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


fun checkCredentials(loginData: LoginData, context: Context): Boolean {
    return if (loginData.isNotEmpty() && loginData.login == "admin") {
        context.startActivity(Intent(context, MainActivity::class.java))
        (context as Activity).finish()
        true
    } else {
        Toast.makeText(context, context.resources.getString(R.string.login_toast_error), Toast.LENGTH_SHORT).show()
        false
    }
}