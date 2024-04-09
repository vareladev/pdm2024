package com.evarela.ucalifes02.uicomponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evarela.ucalifes02.R
import com.evarela.ucalifes02.model.PostDataClass


@Composable
fun PostComponent(post: PostDataClass){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        PostTitle(post.title)
        PostPhoto(post.postImageId)
        PostInfo(post.likeCount, post.date)
        PostDescription(post.description)
    }
}

@Composable
fun PostTitle(postTitle: String){
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ){
        Text(
            text = postTitle
        )
    }
}

@Composable
fun PostPhoto(postImageId: Int){
    Box (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Image(
            modifier = Modifier
                .aspectRatio(4/3f),
            painter = painterResource(id = postImageId),
            contentDescription = "Post photo",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun PostInfo(likeCount: Int, date: String){
    Row (
        modifier = Modifier
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_like_outlined),
            contentDescription = "like's icon"
        )
        Text(
            modifier = Modifier
                .padding(start=4.dp),
            text = likeCount.toString()
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Text(text = date)

    }
}

@Composable
fun PostDescription(postDescription: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp)
    ){
        Text(
            text = postDescription,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun PreviewPostComponent(){
    PostComponent(
        PostDataClass(
            1,
            "example",
            R.drawable.landscape,
            6,
            "02/02/2024",
            "Sit ducimus quis qui quia quia in repellat veritatis sed vero nulla et architecto totam qui quidem laudantium. Et voluptatem rerum et tempora sunt qui officiis galisum in ipsum magnam aut eligendi explicabo et vero nemo. Est architecto accusamus id nemo illo 33 dolorem voluptatibus ut soluta quam aut totam esse. At eveniet nihil At placeat quae sed sequi praesentium qui perspiciatis numquam est amet sequi aut quasi tempora et esse dicta."
        )
    )
}