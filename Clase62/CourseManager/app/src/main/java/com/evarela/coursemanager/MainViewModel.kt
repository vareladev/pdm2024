package com.evarela.coursemanager

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evarela.coursemanager.data.courseList
import com.evarela.coursemanager.data.database.MyApplication
import com.evarela.coursemanager.data.database.entity.Course
import com.evarela.coursemanager.data.database.entity.Topic
import com.evarela.coursemanager.data.topicList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // Estados que gestionan el estado de la interfaz
    private val _uiState = MutableStateFlow<UiState>(UiState.Ready)
    val uiState : StateFlow<UiState> = _uiState

    // Estados que gestionan la informacion de EditScreen
    private val _coursedata = mutableStateOf(Course())
    val coursedata : State<Course> = _coursedata

    private val db = MyApplication.database

    // Insert new course
    fun insertCourse(course: Course, topicList: MutableList<Topic>){
        Log.i("MainViewModel", "Ejecutando función en viewModel")
        viewModelScope.launch (Dispatchers.IO){
            try {
                // inserting course
                db.courseDao().insertCourse(course)
                // Inserting course topics
                for (topic in topicList){
                    db.topicDao().insertTopic(topic)
                }
                _uiState.value = UiState.Success("Curso agregado correctamente")
            }
            catch (e: Exception){
                when (e){
                    is SQLiteConstraintException -> {
                        Log.i("MainViewModel", "Error: codigo de curso duplicado")
                        _uiState.value = UiState.Error("Error: codigo de curso duplicado")
                    }
                    else -> {
                        Log.i("MainViewModel", e.toString())
                        _uiState.value = UiState.Error("Error al intentar acceder a la base de datos")
                    }
                }
            }
        }
    }

    // Select all courses
    //courseList
    fun getAllCourses(){
        viewModelScope.launch (Dispatchers.IO) {
            try {
                val courses = db.courseDao().getAllCourses()
                courseList.clear()
                for (course in courses){
                    courseList.add(course)
                }
            } catch (e: Exception) {
                Log.i("MainViewModel", e.toString())
                _uiState.value = UiState.Error("Error al intentar acceder a la base de datos")
            }
        }
    }

    // Select specific course
    fun getCourse(code : String){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val course = db.courseDao().getCourse(code)
                _coursedata.value = course
                // obteniendo lista de temas
                val myTopicList = db.topicDao().selectAllTopics(code)
                topicList.clear()
                for (t in myTopicList){
                    topicList.add(t)
                }
            }
            catch (e: Exception) {
                Log.i("MainViewModel", e.toString())
                _uiState.value = UiState.Error("Error al intentar acceder a la base de datos")
            }
        }

    }

    // Updating course
    fun updateCourse(course: Course){
        viewModelScope.launch (Dispatchers.IO){
            try {
                db.courseDao().updateCourse(course)
                _uiState.value = UiState.Success("Curso actualizado con éxito")
            }
            catch (e: Exception) {
                Log.i("MainViewModel", e.toString())
                _uiState.value = UiState.Error("Error al intentar acceder a la base de datos")
            }
        }
    }

    // Updating course
    fun deleteCourse(course: Course){
        viewModelScope.launch (Dispatchers.IO){
            try {
                db.courseDao().deleteCourse(course)
                _uiState.value = UiState.Success("Curso eliminado con éxito")
            }
            catch (e: Exception) {
                Log.i("MainViewModel", e.toString())
                _uiState.value = UiState.Error("Error al intentar acceder a la base de datos")
            }
        }
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