package com.evarela.coursemanager.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.evarela.coursemanager.MainViewModel
import com.evarela.coursemanager.UiState
import com.evarela.coursemanager.data.courseList
import com.evarela.coursemanager.data.database.entity.Course
import com.evarela.coursemanager.ui.component.ListItem
import com.evarela.coursemanager.ui.component.LoadingProgressDialog
import com.evarela.coursemanager.ui.component.TopBar
import com.evarela.coursemanager.ui.navigation.ScreenRoute

@Composable
fun HomeScreen(
    viewModel : MainViewModel,
    navController : NavController
){
    LaunchedEffect(Unit){
        viewModel.getAllCourses()
    }


    // Estado para controlar el estado de la interfaz desde viewModel
    val homeScreenState = viewModel.uiState.collectAsState()
    when(homeScreenState.value){
        is UiState.Error -> {
            val message = (homeScreenState.value as UiState.Error).msg
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
            viewModel.setStateToReady()
        }
        UiState.Loading -> {
            LoadingProgressDialog()
        }
        else -> {}
    }

    Scaffold (
        topBar = {
                 TopBar(
                     title = "Course Manager",
                     navController = navController
                 )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(ScreenRoute.Add.route) }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ){ paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            items(courseList){ course ->
                ListItem(
                    course,
                    onItemClick = { selectedCourse ->
                        navController.navigate(route = "${ScreenRoute.Edit.route}/${course.id}")
                    }
                )
                Divider(thickness = 1.dp, color = Color.LightGray)
            }
        }
    }

}

