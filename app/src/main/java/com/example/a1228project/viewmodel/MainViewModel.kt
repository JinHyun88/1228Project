package com.example.a1228project.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.a1228project.database.Todo
import com.example.a1228project.database.TodoDatabase
import com.example.a1228project.repo.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application){
    val getAllData : LiveData<List<Todo>>
    val getDoneData : LiveData<List<Todo>>
    private val repository : TodoRepository

    private var _currentData = MutableLiveData<List<Todo>>()
    val currentData : LiveData<List<Todo>>
        get() = _currentData

    init{
        val todoDao = TodoDatabase.getDatabase(application)!!.todoDao()
        repository = TodoRepository(todoDao)
        getAllData = repository.getAllData.asLiveData()
        getDoneData = repository.getDoneData.asLiveData()
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

    fun getDateData(year : Int, month : Int, day : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val tmp = repository.getDateData(year, month, day)
            _currentData.postValue(tmp!!)
        }
    }
}