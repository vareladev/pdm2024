package com.evarela.coursemanager

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.evarela.coursemanager.data.courseList
import com.evarela.coursemanager.data.database.MyApplication
import com.evarela.coursemanager.data.database.entity.Course
import com.evarela.coursemanager.data.database.entity.Topic
import com.evarela.coursemanager.data.topicList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class MainViewModel : ViewModel() {

    // Estados que gestionan el estado de la interfaz:
    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    val uiState : StateFlow<UiState> = _uiState

    // Estados que gestionan la informacion de EditScreen
    private val _coursedata = mutableStateOf(Course())
    val coursedata : State<Course> = _coursedata

    private val db = MyApplication.database

    // Insert new course
    fun insertCourse(course: Course, topicList: MutableList<Topic>){
        _uiState.value = UiState.Success("Curso agregado correctamente")
    }

    // Select all courses
    //courseList
    fun getAllCourses(){
        courseList.clear()
        courseList.add(
            Course("PDM","Programación de dispositivo móviles","curso","desarrollo")
        )
    }

    // Select specific course
    fun getCourse(code : String){
        _coursedata.value = Course("PDM","Programación de dispositivo móviles","curso","desarrollo")
        // obteniendo lista de temas
        topicList.clear()
        topicList.add(
            Topic(1, "fundamentos de Android", "PDM"),
        )
        topicList.add(
            Topic(2, "Kotlin", "PDM"),
        )
    }

    // Updating course
    fun updateCourse(course: Course){
        _uiState.value = UiState.Success("Curso actualizado con éxito")
    }

    // deleting course
    fun deleteCourse(course: Course){
        _uiState.value = UiState.Success("Curso eliminado con éxito")
    }


    fun setStateToReady() {
        _uiState.value = UiState.Ready
    }
}

sealed class UiState {
    data object Ready : UiState()
    data class Success (val msg : String) : UiState()
    data class Error (val msg : String) : UiState()
}