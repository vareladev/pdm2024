package com.evarela.example221jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListComponent()
        }
    }
}

@Composable
fun MyComponent(text: String){
    Text(
        modifier = Modifier
            .padding(8.dp)
            .border(2.dp, Color.DarkGray)
            .background(Color.Black)
            .wrapContentHeight(Alignment.CenterVertically),
        text = text.repeat(5),
        fontSize = 32.sp,
        fontWeight = FontWeight.Light,
        color = Color.Yellow,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis

    )
}

@Composable
fun ListComponent(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ){
        MyComponent("Mobile development 2024")
        MyComponent("Web development 2024")
        MyComponent("QA 2024")
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyComponentPreview(){
    ListComponent()
}