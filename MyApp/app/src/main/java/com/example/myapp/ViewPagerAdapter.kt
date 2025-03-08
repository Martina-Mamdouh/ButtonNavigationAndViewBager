package com.example.myapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapp.fragment.CricketFragment
import com.example.myapp.fragment.FootballFragment
import com.example.myapp.fragment.HockeyFragment

class ViewPagerAdapter(fragmentActivity:FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
           0->CricketFragment()
           1->FootballFragment()
           2->HockeyFragment()
           else->CricketFragment()
       }
    }

}