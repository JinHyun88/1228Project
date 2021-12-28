package com.example.a1228project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a1228project.database.Todo
import com.example.a1228project.database.TodoDatabase
import com.example.a1228project.repo.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application){
    val getAllData : LiveData<List<Todo>>
    private val repository : TodoRepository

    init{
        val todoDao = TodoDatabase.getDatabase(application)!!.todoDao()
        repository = TodoRepository(todoDao)
        getAllData = repository.getAllData.asLiveData()
    }

    fun addTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTodo(todo)
        }
    }

    fun updateTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodo(todo)
        }
    }

    fun getDateData(year : Int, month : Int, day : Int): LiveData<List<Todo>> {
        return repository.getDateData(year, month, day).asLiveData()
    }
}