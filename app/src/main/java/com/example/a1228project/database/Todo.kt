package com.example.a1228project.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val check : Boolean,
    val content : String,
    val year : Int,
    val month : Int,
    val day : Int
    )
