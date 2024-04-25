package com.evarela.automatizedlightbulb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.evarela.automatizedlightbulb.ui.theme.AutomatizedLightBulbTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel = MainViewModel()
        setContent {
            AutomatizedLightBulbTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BulbLight(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun BulbLight(
    mainViewModel: MainViewModel
){
    val switchState by mainViewModel
        .switchControllerFlow
        .collectAsState()

    Column(modifier = Modifier
        .padding(25.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Switch(
            checked = switchState,
            onCheckedChange = {}
        )
        Spacer(modifier = Modifier.padding(24.dp))
        Image(
            modifier = Modifier
                .height(256.dp)
                .width(256.dp),
            painter = if (switchState) painterResource(id = R.drawable.img_bulb_on) else  painterResource(id = R.drawable.img_bulb_off),
            contentDescription = "Bulblight Status")

        if(switchState)
        {
            Text("Switch is on.")
        }else {
            Text("Switch is off.")
        }
    }
}
