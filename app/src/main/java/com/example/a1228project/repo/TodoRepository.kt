package com.example.a1228project.repo

import com.example.a1228project.database.Todo
import com.example.a1228project.database.TodoDao
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val todoDao: TodoDao) {

    val getAllData : Flow<List<Todo>> = todoDao.getAllData()
    val getDoneData : Flow<List<Todo>> = todoDao.getDoneData()

    suspend fun addTodo(todo: Todo){
        todoDao.addTodo(todo)
    }

    suspend fun updateTodo(todo: Todo){
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo){
        todoDao.deleteTodo(todo)
    }

    fun getDateData(year : Int, month : Int, day : Int): List<Todo> {
        return todoDao.getDateData(year, month, day)
    }
}