package com.example.a1228project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a1228project.R
import com.example.a1228project.database.Todo
import com.example.a1228project.databinding.TodoItemBinding
import com.example.a1228project.dialog.UpdateDialog
import com.example.a1228project.dialog.UpdateDialogInterface
import com.example.a1228project.viewmodel.MainViewModel

class TodoAdapter(private val todoViewModel: MainViewModel) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var todoList = emptyList<Todo>()

    class TodoViewHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root),
        UpdateDialogInterface {

        lateinit var todo : Todo
        lateinit var todoViewModel: MainViewModel
        fun bind(currentTodo : Todo, memoViewModel: MainViewModel){
            binding.todo = currentTodo

            binding.memoCheckBox.setOnCheckedChangeListener(null)

            binding.memoCheckBox.setOnCheckedChangeListener { _, check ->
                if (check) {
                    val todo = Todo(currentTodo.id, true, currentTodo.content,
                        currentTodo.year, currentTodo.month, currentTodo.day)
                    memoViewModel.updateTodo(todo)
                }
                else {
                    val todo = Todo(currentTodo.id, false, currentTodo.content,
                        currentTodo.year, currentTodo.month, currentTodo.day)
                    memoViewModel.updateTodo(todo)
                }
            }

            binding.deleteButton.setOnClickListener {
                memoViewModel.deleteTodo(currentTodo)
            }

            binding.updateButton.setOnClickListener {
                todo = currentTodo
                val myCustomDialog = UpdateDialog(binding.updateButton.context,this)
                myCustomDialog.show()
            }
        }

        override fun onOkButtonClicked(content: String) {
            val updateTodo = Todo(todo.id,todo.check,content,todo.year,todo.month,todo.day)
            todoViewModel.updateTodo(updateTodo)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position],todoViewModel)

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun setData(memo : List<Todo>){
        todoList = memo
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}