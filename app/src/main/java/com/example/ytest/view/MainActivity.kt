package com.example.ytest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ytest.R
import com.example.ytest.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setupUi()

        setContentView(binding.root)
    }

    private fun setupUi() {
        with(binding.mainViewPager) {
            adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
//            binding.tabs.setupWithViewPager(this)
        }
//        binding.tabs.setupWithViewPager(bind.)

        val tabText = listOf("첫번째", "두번째")

        TabLayoutMediator(binding.tabs, binding.mainViewPager) { tab, position ->
            tab.text = tabText[position]
            binding.mainViewPager.setCurrentItem(position, true)
        }.attach()
    }
}