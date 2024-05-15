package com.evarela.coursemanager.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evarela.coursemanager.data.database.entity.Topic

@Dao
interface TopicDAO {
    // Insert topic
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun  insertTopic(topic: Topic)

    // select all topics
    @Query("SELECT * FROM Topic WHERE codeCourse=:codeCourse")
    suspend fun selectAllTopics(codeCourse : String) : MutableList<Topic>

}