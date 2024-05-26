package com.evarela.apirestlogin.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.evarela.apirestlogin.R
import com.evarela.apirestlogin.data.api.UserData


@Composable
fun UserCard(userData : UserData){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(Color.White)
    ){
        Box (
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f),
            Alignment.Center
        ){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userData.profilePic)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_user),
                contentDescription = "${userData.firstName} ${userData.lastName} profile pic",
                contentScale = ContentScale.None,
                modifier = Modifier
                    .clip(CircleShape)

            )
        }
        Column (
            modifier = Modifier
                .weight(0.8f),
        ){
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                Alignment.BottomStart
            ){
                Text(
                    text = "${userData.firstName} ${userData.lastName}",
                    fontWeight = FontWeight.Bold
                )
            }
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ){
                Text(text = userData.email)
            }

        }
    }
    Divider(thickness = 1.dp, color = Color.LightGray)
}




@Preview
@Composable
fun PreviewUserCard(){
    UserCard(
        UserData(1,
            "evarela@uca.edu.sv",
            "Erick",
            "Varela",
            "")
    )
}