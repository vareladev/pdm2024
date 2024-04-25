package com.evarela.ucalife.component.post


import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evarela.ucalife.R
import com.evarela.ucalife.model.PostDataModel
import com.evarela.ucalife.ui.theme.Black

@Composable
fun Post(post: PostDataModel){
    Column (
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Text(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = post.title,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4 / 3F),
            painter = painterResource(id = post.image),
            contentDescription = "Photo",
            contentScale = ContentScale.Crop
        )
        Row (
            modifier = Modifier
                .padding(top=8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_like_outlined),
                contentDescription = "Like count",
                colorFilter = ColorFilter.tint(
                    if (isSystemInDarkTheme())
                        MaterialTheme.colorScheme.tertiary
                    else
                        Black
                )
            )
            Text(
                modifier = Modifier.padding(start=4.dp),
                text = post.likeCount.toString()
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = post.date,
                fontSize = 10.sp
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = post.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }


    }

}


@Preview
@Composable
fun PreviewPost(){
    Post(PostDataModel(
        1,
        "Example",
        R.drawable.portrait,
        5,
        "01/01/2024",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    ))
}