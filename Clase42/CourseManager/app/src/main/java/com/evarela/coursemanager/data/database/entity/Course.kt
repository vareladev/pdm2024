package com.evarela.coursemanager.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Course")
data class Course(
    @PrimaryKey
    @ColumnInfo(name = "code")
    val code : String = "",

    @ColumnInfo(name = "title")
    val title : String = "",

    @ColumnInfo(name = "description")
    val description : String = "",

    @ColumnInfo(name = "category")
    val category : String = ""
)
