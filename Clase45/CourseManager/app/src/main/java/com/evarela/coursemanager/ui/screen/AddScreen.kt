package com.evarela.coursemanager.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.evarela.coursemanager.MainViewModel
import com.evarela.coursemanager.UiState
import com.evarela.coursemanager.data.api.CourseApi
import com.evarela.coursemanager.data.api.TopicApi
import com.evarela.coursemanager.data.database.entity.Course
import com.evarela.coursemanager.data.database.entity.Topic
import com.evarela.coursemanager.data.topicList
import com.evarela.coursemanager.ui.component.LoadingProgressDialog
import com.evarela.coursemanager.ui.component.TopBar
import com.evarela.coursemanager.ui.theme.LightGray

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddScreen(
    viewModel : MainViewModel,
    navController : NavController
){
    LaunchedEffect (Unit){
        topicList.clear()
    }

    // Estado que almacena la informacion del curso
    var coursedata by remember { mutableStateOf(CourseApi()) }
    // Estado que almacena la informacion de los temas
    var topictitle by remember { mutableStateOf("") }

    // Controller que gestiona el teclado
    val keyBoardController = LocalSoftwareKeyboardController.current

    // Estado para controlar el estado de la interfaz desde viewModel
    val addScreenState = viewModel.uiState.collectAsState()
    when(addScreenState.value){
        is UiState.Error -> {
            val message = (addScreenState.value as UiState.Error).msg
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
            viewModel.setStateToReady()
        }
        UiState.Loading -> {
            LoadingProgressDialog()
        }
        UiState.Ready -> {}
        is UiState.Success -> {
            val message = (addScreenState.value as UiState.Success).msg
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
            viewModel.setStateToReady()
            navController.popBackStack()
        }
    }


    Scaffold (
        topBar = {
            TopBar(
                title = "Add course",
                navController = navController,
                onSaveEvent = {
                    keyBoardController?.hide()
                    viewModel.insertCourse(coursedata)
                }
            )
        },
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = coursedata.code,
                onValueChange = { updatedString -> coursedata = coursedata.copy(code = updatedString) },
                label = { Text(text = "Code") },
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = coursedata.title,
                onValueChange = { updatedString -> coursedata = coursedata.copy(title = updatedString) },
                label = { Text(text = "Title") },
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(128.dp),
                value = coursedata.description,
                onValueChange = { updatedString -> coursedata = coursedata.copy(description = updatedString) },
                label = { Text(text = "Description") },
                singleLine = false
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = coursedata.category,
                onValueChange = { updatedString -> coursedata = coursedata.copy(category = updatedString) },
                label = { Text(text = "Category") },
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                OutlinedTextField(
                    modifier = Modifier.weight(0.7f),
                    value = topictitle,
                    onValueChange = { updatedString -> topictitle = updatedString},
                    label = { Text(text = "Insert topic") },
                )
                Button(
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(12.dp),
                    onClick = {
                        keyBoardController?.hide()
                        topicList.add(
                            Topic(title  = topictitle)
                        )
                        coursedata.topicList.add( TopicApi( title = topictitle ) )
                        topictitle = ""
                    },
                    enabled = if(topictitle.isEmpty()) false else true
                )
                {
                    Text(text = "Add")
                }
            }
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .background(LightGray)
            ){
                items(topicList){ topic ->
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        text = topic.title
                    )
                    Divider(thickness = 1.dp, color = Color.Gray)
                }
            }
        }
    }
}


