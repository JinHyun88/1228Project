package com.example.a1228project.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.a1228project.fragment.CalFragment
import com.example.a1228project.fragment.DoneFragment
import com.example.a1228project.fragment.TodoFragment

class ViewPagerAdapter (fragment : FragmentActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TodoFragment()
            1 -> CalFragment()
            else ->DoneFragment()
        }
    }
}