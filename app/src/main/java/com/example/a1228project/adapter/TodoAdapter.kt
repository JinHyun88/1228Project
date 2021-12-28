package com.example.a1228project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a1228project.R
import com.example.a1228project.database.Todo
import com.example.a1228project.databinding.TodoItemBinding
import com.example.a1228project.viewmodel.MainViewModel

class TodoAdapter(private val todoViewModel: MainViewModel) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var todoList = emptyList<Todo>()

    class TodoViewHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root){
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

            // 삭제 버튼 클릭 시 메모 삭제
            binding.deleteButton.setOnClickListener {
                memoViewModel.deleteTodo(currentTodo)
            }
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