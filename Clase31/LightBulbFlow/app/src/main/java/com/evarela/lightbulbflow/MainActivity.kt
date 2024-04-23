package com.evarela.lightbulbflow

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evarela.lightbulbflow.ui.theme.LightBulbFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ********************* Ejemplo flows *******************
        val mainViewModel = MainViewModel()
        mainViewModel.flowListener()


        setContent {
            LightBulbFlowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    lightBulb()
                }
            }
        }
    }
}

@Composable
fun lightBulb(){
    val checked = remember { mutableStateOf(true) }

    Column(modifier = Modifier
        .padding(25.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Switch(
            checked = checked.value,
            onCheckedChange = {checked.value = it}
        )
        Spacer(modifier = Modifier.padding(24.dp))
        Image(
            modifier = Modifier
                .height(256.dp)
                .width(256.dp),
            painter = if (checked.value) painterResource(id = R.drawable.img_bulb_on) else  painterResource(id = R.drawable.img_bulb_off),
            contentDescription = "Bulblight Status")

        if(checked.value)
        {
            Text("Switch is on.")
        }else {
            Text("Switch is off.")
        }
    }
}