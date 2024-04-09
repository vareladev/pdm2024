package com.evarela.ucalifes02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.evarela.ucalifes02.model.PostDataClass
import com.evarela.ucalifes02.ui.theme.UcaLifeS02Theme
import com.evarela.ucalifes02.uicomponent.PostComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UcaLifeS02Theme {
                Column {
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main(){
    PostComponent(post =
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

@Preview(showSystemUi= true)
@Composable
fun PreviewMain(){
    Column {
        PostComponent(post =
        PostDataClass(
            1,
            "example",
            R.drawable.landscape,
            6,
            "02/02/2024",
            "Sit ducimus quis qui quia quia in repellat veritatis sed vero nulla et architecto totam qui quidem laudantium. Et voluptatem rerum et tempora sunt qui officiis galisum in ipsum magnam aut eligendi explicabo et vero nemo. Est architecto accusamus id nemo illo 33 dolorem voluptatibus ut soluta quam aut totam esse. At eveniet nihil At placeat quae sed sequi praesentium qui perspiciatis numquam est amet sequi aut quasi tempora et esse dicta."
        )
        )
        PostComponent(post =
        PostDataClass(
            1,
            "example",
            R.drawable.portrait,
            6,
            "02/02/2024",
            "Sit ducimus quis qui quia quia in repellat veritatis sed vero nulla et architecto totam qui quidem laudantium. Et voluptatem rerum et tempora sunt qui officiis galisum in ipsum magnam aut eligendi explicabo et vero nemo. Est architecto accusamus id nemo illo 33 dolorem voluptatibus ut soluta quam aut totam esse. At eveniet nihil At placeat quae sed sequi praesentium qui perspiciatis numquam est amet sequi aut quasi tempora et esse dicta."
        )
        )
        PostComponent(post =
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
}