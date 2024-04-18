package com.evarela.ucalife.component.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evarela.ucalife.R
import com.evarela.ucalife.ui.theme.DarkBackgroundGray
import com.evarela.ucalife.ui.theme.Gray

@Composable
fun AddPostComponent(){
    Column (
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        AddPostHeader()
        AddImage()
        EditTextComponent(stringResource(id = R.string.insert_title))
        Textarea(stringResource(id = R.string.insert_description))
    }
}

@Composable
fun AddPostHeader(){
    Row (
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = stringResource(id = R.string.select_photo),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor =
                if(isSystemInDarkTheme())
                    MaterialTheme.colorScheme.secondary
                else
                    MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.tertiary
            )
        ) {
            Text(stringResource(id = R.string.post_photo))
        }
    }
}



@Composable
fun AddImage(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(4 / 3F)
            .background(
                if (isSystemInDarkTheme())
                    Gray
                else
                    MaterialTheme.colorScheme.tertiary
            )
            .clickable { /*TODO*/ },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Image(
            modifier = Modifier
                .width(60.dp),
            painter = painterResource(id = R.drawable.ic_camera_outlined),
            contentDescription = "Photo",
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(
                if (isSystemInDarkTheme())
                    DarkBackgroundGray
                else
                    Gray
            )
        )
    }
}

@Composable
fun EditTextComponent(label: String){
    val maxChar = 30
    var titleCount by remember {
        mutableIntStateOf(0)
    }
    var myText by remember {
        mutableStateOf("")
    }
    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = myText,
            onValueChange = { if (it.length <= maxChar) {
                titleCount = it.length
                myText = it
            }},
            label = { Text(label) },
            singleLine = true
        )
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            Text(
                text = "$titleCount/$maxChar",
                color = Color.Gray
            )
        }
    }


}

@Composable
fun Textarea(label: String) {
    val maxChar = 200
    var titleCount by remember {
        mutableIntStateOf(0)
    }

    var myText by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        value = myText,
        onValueChange = {
            if (it.length <= maxChar) {
                titleCount = it.length
                myText = it
            }
        },
        label = { Text(label) },
        singleLine = false

    )
    Row (
        Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Top
    ){
        Text(
            text = "$titleCount/$maxChar",
            color = Color.Gray
        )
    }
}

@Preview
@Composable
fun PreviewAddPostComponent(){
    AddPostComponent()
}