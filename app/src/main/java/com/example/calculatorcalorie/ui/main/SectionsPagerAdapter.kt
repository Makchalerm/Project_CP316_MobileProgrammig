package com.example.calculatorcalorie.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.calculatorcalorie.Fragment.AboutFragment
import com.example.calculatorcalorie.Fragment.DiaryFragment
import com.example.calculatorcalorie.Fragment.DashboardFragment
import com.example.calculatorcalorie.Fragment.MainActivity

class SectionPagerAdapter(fragment: MainActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment = when (position){
            0 -> DashboardFragment()
            1 -> DiaryFragment()
            2 -> AboutFragment()
            else -> DashboardFragment()
    }

}