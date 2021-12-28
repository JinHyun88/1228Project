package com.example.a1228project.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM Todo ORDER BY year DESC, month DESC, day DESC, id DESC")
    fun getAllData() : Flow<List<Todo>>

    @Query("SELECT * FROM Todo WHERE year = :year AND month = :month AND day = :day ORDER BY id DESC")
    fun getDateData(year : Int, month : Int, day : Int) : List<Todo>

    @Query("SELECT * FROM Todo WHERE `check` = 1 ORDER BY year DESC, month DESC, day DESC, id DESC")
    fun getDoneData() : Flow<List<Todo>>


}