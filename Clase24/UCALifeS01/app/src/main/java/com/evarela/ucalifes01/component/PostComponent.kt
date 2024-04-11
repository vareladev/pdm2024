package com.evarela.ucalifes01.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evarela.ucalifes01.R
import com.evarela.ucalifes01.model.PostDataModel

@Composable
fun PostComponent(post: PostDataModel){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        PostTitle(post.title)
        PostPhoto(post.photo_id)
        PostInfo(post.likeCout, post.date)
        PostDescription(post.description)
    }
}

@Composable
fun PostTitle(title: String){
    Text(
        modifier = Modifier
            .padding(6.dp),
        text = title,
        color = Color.Black
    )
}

@Composable
fun PostPhoto(imageResource: Int){
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(4 / 3f),
        painter = painterResource(id = imageResource),
        contentDescription =stringResource(R.string.post_photo),
        contentScale = ContentScale.Crop
    )

}

@Composable
fun PostInfo(likesCount: Int,  date: String){
    Row (
        modifier = Modifier
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(R.drawable.ic_pokeball),
            contentDescription =
            stringResource(id = R.string.like_count))
        Text(
            modifier = Modifier
                .padding(start = 4.dp),
            text = likesCount.toString()
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Text(text = date)
    }
   
}

@Composable
fun PostDescription(description: String){
    Text(
        modifier =  Modifier
            .fillMaxWidth(),
        text = description,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}
@Preview()
@Composable
fun PreviewPostComponent(){
    PostComponent(
        PostDataModel(
        1,
        "Example",
        R.drawable.portrait,
        6,
        "02/02/2024",
        "No se puede acceder a este sitio No se puede acceder a este sitio No se puede acceder a este sitio No se puede acceder a este sitio No se puede acceder a este sitio No se puede acceder a este sitio No se puede acceder a este sitio No se puede acceder a este sitio No se puede acceder a este sitio"
        )
    )
}