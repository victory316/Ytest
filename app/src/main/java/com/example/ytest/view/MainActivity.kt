package com.example.ytest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ytest.R
import com.example.ytest.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * 최초로 실행되는 기본 Activity
 */

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
        }

        val tabText = listOf(
            resources.getString(R.string.show_all_tab_text),
            resources.getString(R.string.favorite_tab_text)
        )

        TabLayoutMediator(binding.tabs, binding.mainViewPager) { tab, position ->
            tab.text = tabText[position]
            binding.mainViewPager.setCurrentItem(position, true)
        }.attach()

        binding.mainViewPager.setCurrentItem(0, true)

    }
}