package com.evarela.coursemanager.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Topic",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Course::class,
            parentColumns = arrayOf("code"),
            childColumns = arrayOf("codeCourse"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    )
)
data class Topic(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = 0,

    @ColumnInfo(name = "title")
    val title : String = "",

    //FK: Course -> Topic
    @ColumnInfo(name = "codeCourse", index = true)
    var codeCourse : String = ""
)
