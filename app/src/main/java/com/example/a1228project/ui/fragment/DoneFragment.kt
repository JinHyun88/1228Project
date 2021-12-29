package com.example.a1228project.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a1228project.R
import com.example.a1228project.adapter.TodoAdapter
import com.example.a1228project.databinding.FragmentDoneBinding
import com.example.a1228project.viewmodel.MainViewModel

class DoneFragment : Fragment() {

    private var binding : FragmentDoneBinding? = null
    private val todoViewModel: MainViewModel by viewModels() // 뷰모델 연결
    private val adapter : TodoAdapter by lazy { TodoAdapter(todoViewModel) } // 어댑터 선언

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoneBinding.inflate(inflater,container,false)

        // 아이템에 아이디를 설정해줌 (깜빡이는 현상방지)
        adapter.setHasStableIds(true)

        // 아이템을 가로로 하나씩 보여주고 어댑터 연결
        binding!!.doneRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        binding!!.doneRecyclerView.adapter = adapter

        // 리스트 관찰하여 변경시 어댑터에 전달해줌
        todoViewModel.getDoneData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        return binding!!.root
    }

}