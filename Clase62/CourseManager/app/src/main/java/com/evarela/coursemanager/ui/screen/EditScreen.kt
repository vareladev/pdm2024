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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.evarela.coursemanager.data.courseList
import com.evarela.coursemanager.data.database.entity.Course
import com.evarela.coursemanager.data.database.entity.Topic
import com.evarela.coursemanager.data.topicList
import com.evarela.coursemanager.ui.component.ListItem
import com.evarela.coursemanager.ui.component.TopBar
import com.evarela.coursemanager.ui.theme.LightGray

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditScreen(
    viewModel : MainViewModel,
    navController : NavController,
    courseCode : String?
){
    LaunchedEffect (Unit){
        if (courseCode != null){
            viewModel.getCourse(courseCode)
        }
    }

    // Estado que almacena la informacion del curso
    var coursedata by remember { mutableStateOf(Course()) }
    // Estado que almacena la informacion de los temas
    var topictitle by remember { mutableStateOf("") }
    // Estado que cambia el comportamiendo de los campos de texto (los habilita para cambios)
    var isFormEnabled by remember { mutableStateOf(false) }
    // Estado que gestiona cuando la ventana emergente de eliminar es visible
    var isDeleteDialogOpen by remember { mutableStateOf(false) }

    // Controller que gestiona el teclado
    val keyBoardController = LocalSoftwareKeyboardController.current

    // obteniendo informacion del curso desde viewModel
    val fetchedCourse by viewModel.coursedata
    coursedata = fetchedCourse

    // Estado para controlar el estado de la interfaz desde viewModel
    val editScreenState = viewModel.uiState.collectAsState()

    if(editScreenState.value is UiState.Error){
        val message = (editScreenState.value as UiState.Error).msg
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
        viewModel.setStateToReady()
    }
    if(editScreenState.value is UiState.Success){
        val message = (editScreenState.value as UiState.Success).msg
        Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
        viewModel.setStateToReady()
        navController.popBackStack()
    }

    Scaffold (
        topBar = {
            TopBar(
                title = "Edit course",
                navController = navController,
                onSaveEvent = {
                    keyBoardController?.hide()
                    viewModel.updateCourse(coursedata)
                },
                onDeleteEvent = {
                    isDeleteDialogOpen = true
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ){
                OutlinedTextField(
                    modifier = Modifier
                        .weight(0.7f),
                    value = courseCode ?: "",
                    onValueChange = { updatedString -> coursedata = coursedata.copy(code = updatedString) },
                    label = { Text(text = "Code") },
                    enabled = false
                )
                Button(
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(12.dp),
                    onClick = { isFormEnabled = true }
                ) {
                    Text(text = "Edit")
                }
            }
            Divider(thickness = 1.dp, color = Color.Gray)

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = coursedata.title,
                onValueChange = { updatedString -> coursedata = coursedata.copy(title = updatedString) },
                label = { Text(text = "Title") },
                enabled = isFormEnabled
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(128.dp),
                value = coursedata.description,
                onValueChange = { updatedString -> coursedata = coursedata.copy(description = updatedString) },
                label = { Text(text = "Description") },
                singleLine = false,
                enabled = isFormEnabled
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = coursedata.category,
                onValueChange = { updatedString -> coursedata = coursedata.copy(category = updatedString) },
                label = { Text(text = "Category") },
                enabled = isFormEnabled
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
                    enabled = isFormEnabled
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

    if (isDeleteDialogOpen){
        AlertDialog(
            onDismissRequest = { isDeleteDialogOpen = false },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteCourse(coursedata)
                        isDeleteDialogOpen = false
                    }
                ) {
                    Text(text = "Elminar")
                }
            },
            dismissButton = {
                Button(
                    onClick = { isDeleteDialogOpen = false }
                ) {
                    Text(text = "Cancelar")
                }
            },
            text = {
                Text(text = "¿Desea eliminar este curso?")
            }
        )
    }

}