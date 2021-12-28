package com.example.a1228project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a1228project.R
import com.example.a1228project.adapter.TodoAdapter
import com.example.a1228project.database.Todo
import com.example.a1228project.databinding.FragmentTodoBinding
import com.example.a1228project.dialog.MyCustomDialog
import com.example.a1228project.dialog.MyCustomDialogInterface
import com.example.a1228project.viewmodel.MainViewModel
import java.util.*

class TodoFragment : Fragment(), MyCustomDialogInterface {

    private var binding : FragmentTodoBinding? = null
    private val todoViewModel : MainViewModel by viewModels()
    private val adapter : TodoAdapter by lazy { TodoAdapter(todoViewModel) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTodoBinding.inflate(inflater,container,false)

        adapter.setHasStableIds(true)

        // 아이템을 가로로 하나씩 보여주고 어댑터 연결
        binding!!.todoRecyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        binding!!.todoRecyclerView.adapter = adapter

        // 리스트 관찰하여 변경시 어댑터에 전달해줌
        todoViewModel.getAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })



        binding!!.dialogButton.setOnClickListener {
            onFabClicked()
        }

        return binding!!.root
    }

    private fun onFabClicked(){
        val myCustomDialog = MyCustomDialog(requireActivity(), this)
        myCustomDialog.show()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onOkButtonClicked(content: String) {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH) + 1
        val day = cal.get(Calendar.DATE)

        val todo = Todo(0,false,content, year, month, day)
        todoViewModel.addTodo(todo)
        Toast.makeText(activity,"추가", Toast.LENGTH_SHORT).show()
    }


}