package com.evarela.ucalife.component.post

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evarela.ucalife.LoginActivity
import com.evarela.ucalife.MainViewModel
import com.evarela.ucalife.R
import com.evarela.ucalife.model.UserDataModel
import com.evarela.ucalife.ui.theme.BackgroundGray
import com.evarela.ucalife.ui.theme.DarkPrimaryGray
import com.evarela.ucalife.util.changeActivity

@Composable
fun ProfileComponent(
    user: UserDataModel,
    viewModel : MainViewModel
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        ProfilePic(user)
        InfoCard(user)
        ActionsCard(viewModel)
    }
}

@Composable
fun ProfilePic(user: UserDataModel){

    val brush = Brush.verticalGradient(
        if(isSystemInDarkTheme())
            listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.background)
        else
            listOf(MaterialTheme.colorScheme.primary, Color.White)
    )
    Column (
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(brush),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .border(10.dp, Color.LightGray, CircleShape),
            painter = painterResource(id = R.drawable.user_profile_pic),
            contentDescription = "User profile photo"
        )
        Box(
            modifier = Modifier
                .background(
                    color = Color.White.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(60.dp)
                ),
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp,4.dp),
                text = user.username
            )
        }


    }
}

@Composable
fun ActionsCard(
    viewModel: MainViewModel
){
    val context = LocalContext.current
    val signOutState = viewModel.signOutState.collectAsState()
    val openDialog = remember { mutableStateOf(false)  }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 8.dp, end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
    ) {
        Text(
            text = stringResource(id = R.string.actions),
            fontSize  = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color =
                    if (isSystemInDarkTheme())
                        DarkPrimaryGray
                    else
                        BackgroundGray,
                    shape = RoundedCornerShape(8.dp)
                ),
        ) {
            ProfileText(
                Modifier
                    .padding(16.dp, 4.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.change_password)
            )
            Divider(thickness = 1.dp, color = Color.LightGray)
            TextButton(
                onClick = {
                    openDialog.value = true
                },
                shape = CutCornerShape(0f)
            ) {
                ProfileText(
                    Modifier
                        .padding(16.dp, 4.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.sign_out)
                )
            }
            Divider(thickness = 1.dp, color = Color.LightGray)
            ProfileText(
                Modifier
                    .padding(16.dp, 4.dp)
                    .fillMaxWidth(),
                stringResource(id = R.string.delete_account),
                Color.Red,
                FontWeight.SemiBold
            )
        }

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                text = {
                    Text("¿Cerrar sesión?")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.setRememberMe(false)
                            openDialog.value = false
                        }) {
                        Text("Si")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("No")
                    }
                }
            )
        }

        if(signOutState.value){
            changeActivity(context, LoginActivity::class.java)
        }
    }
}

@Composable
fun InfoCard(user: UserDataModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 8.dp, end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
    ) {
        Text(
            text = stringResource(id = R.string.user_information),
            fontSize  = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color =
                    if (isSystemInDarkTheme())
                        DarkPrimaryGray
                    else
                        BackgroundGray,
                    shape = RoundedCornerShape(8.dp)
                ),
        ) {
            ProfileText(
                Modifier
                    .padding(16.dp, 4.dp)
                    .fillMaxWidth(),
                text = "${stringResource(id = R.string.email)}: ${user.email}"
            )
            Divider(thickness = 1.dp, color = Color.LightGray)
            ProfileText(
                Modifier
                    .padding(16.dp, 4.dp)
                    .fillMaxWidth(),
                text = "${stringResource(id = R.string.uploaded_photos)}: ${user.UploadedPhotosCount}"
            )
        }
    }
}

@Composable
fun ProfileText(
    modifier: Modifier,
    text : String,
    color : Color = if (isSystemInDarkTheme()) Color.White  else Color.Black,
    fontWeight : FontWeight = FontWeight.Normal
){
    Text(
        modifier = modifier,
        text = text,
        color = color,
        fontWeight = fontWeight
    )
}



