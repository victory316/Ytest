package com.example.ytest.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.ytest.R
import com.example.ytest.data.local.Product
import com.example.ytest.view.fragments.FirstTabFragment
import com.example.ytest.view.fragments.SecondFragment

/**
 *  ViewPager2에 사용하기 위한 FragmentStateAdapter
 */

class ViewPagerAdapter(fm: FragmentManager?, lifecycle: Lifecycle) : FragmentStateAdapter(fm!!, lifecycle){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return if (position == 0) {
            FirstTabFragment.newInstance()
        } else {
            SecondFragment.newInstance("", "")
        }
    }

}