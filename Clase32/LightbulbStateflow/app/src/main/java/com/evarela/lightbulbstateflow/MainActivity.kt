package com.evarela.lightbulbstateflow

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.evarela.lightbulbstateflow.ui.theme.LightbulbStateflowTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ********************* Ejemplo state flows *******************
        val mainViewModel = MainViewModel()
        mainViewModel.flowConsumer()
        /*
        lifecycleScope.launch(Dispatchers.Default){
            var aux = true
            while(true){
                if (aux != mainViewModel.flowState.value){
                    Log.i("MainActivity", "Valor del estado: ${mainViewModel.flowState.value.toString()}")
                    aux = mainViewModel.flowState.value
                }

            }
        }*/

        setContent {
            LightbulbStateflowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    lightBulb(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun lightBulb(mainViewModel: MainViewModel){
    //val checked = remember { mutableStateOf(true) }
    val checked by mainViewModel.flowState.collectAsState()


    Column(modifier = Modifier
        .padding(25.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Switch(
            checked = checked,
            onCheckedChange = {it}
        )
        Spacer(modifier = Modifier.padding(24.dp))
        Image(
            modifier = Modifier
                .height(256.dp)
                .width(256.dp),
            painter = if (checked) painterResource(id = R.drawable.img_bulb_on) else  painterResource(id = R.drawable.img_bulb_off),
            contentDescription = "Bulblight Status")

        if(checked)
        {
            Text("Switch is on.")
        }else {
            Text("Switch is off.")
        }
    }
}
