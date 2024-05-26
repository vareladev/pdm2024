package com.evarela.coursemanager.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.evarela.coursemanager.data.database.entity.Course

@Dao
interface CourseDAO {
    // Select all courses
    @Query("SELECT * FROM Course")
    suspend fun getAllCourses () : MutableList<Course>

    // Select an specific course
    @Query("SELECT * FROM Course WHERE code=:codeCourse")
    suspend fun getCourse(codeCourse : String) : Course

    // Insert course
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertCourse(course: Course)

    // Update course.
    @Update
    suspend fun updateCourse(course: Course)

    // Delete course
    @Delete
    suspend fun  deleteCourse(course: Course)
}