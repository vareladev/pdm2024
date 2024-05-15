package com.evarela.coursemanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evarela.coursemanager.data.database.dao.CourseDAO
import com.evarela.coursemanager.data.database.dao.TopicDAO
import com.evarela.coursemanager.data.database.entity.Course
import com.evarela.coursemanager.data.database.entity.Topic

@Database(
    entities = [Course::class, Topic::class],
    version = 2
)
abstract class CoursesDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDAO
    abstract fun topicDao(): TopicDAO
}