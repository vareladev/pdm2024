package com.evarela.coursemanager.data

import androidx.compose.runtime.mutableStateListOf
import com.evarela.coursemanager.data.database.entity.Course
import com.evarela.coursemanager.data.database.entity.Topic

val courseList = mutableStateListOf<Course>()

var topicList = mutableStateListOf<Topic>()