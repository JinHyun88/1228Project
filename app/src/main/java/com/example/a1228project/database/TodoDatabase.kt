package com.example.a1228project.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase  : RoomDatabase() {

    abstract fun todoDao() : TodoDao

    companion object{
        @Volatile
        private var instance : TodoDatabase? = null

        fun getDatabase(context : Context) : TodoDatabase? {
            if(instance == null){
                synchronized(TodoDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "todo_database"
                    ).build()
                }
            }
            return instance
        }
    }

}