package com.example.ytest.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ytest.view.fragments.ProductFragment
import com.example.ytest.view.fragments.FavoriteFragment

/**
 *  ViewPager2에 사용하기 위한 FragmentStateAdapter
 */

class ViewPagerAdapter(fm: FragmentManager?, lifecycle: Lifecycle) : FragmentStateAdapter(fm!!, lifecycle){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return if (position == 0) {
            ProductFragment.newInstance()
        } else {
            FavoriteFragment.newInstance("", "")
        }
    }

}