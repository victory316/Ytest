package com.example.ytest.util

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ytest.data.MainRepository
import com.example.ytest.data.DetailRepository
import com.example.ytest.data.MainDatabase
import com.example.ytest.viewmodel.DetailViewModelFactory
import com.example.ytest.viewmodel.MainViewModelFactory

/**
 *
 *  InjectorUtils
 *
 *  - Database와 viewModel의 Injection을 돕는 class
 *
 */

object InjectorUtils {
    private fun getMainRepository(context: Context): MainRepository {
        return MainRepository.getInstance(
            MainDatabase.getInstance(context.applicationContext)!!.answerDao()
        )
    }

    private fun getDetailRepository(context: Context): DetailRepository {
        return DetailRepository.getInstance(
            MainDatabase.getInstance(context.applicationContext)!!.answerDao()
        )
    }

    fun provideMainViewModel(fragment: Fragment): MainViewModelFactory {
        val repository = getMainRepository(fragment.requireContext())
        return MainViewModelFactory(repository, fragment)
    }

    fun provideDetailViewModel(activity: AppCompatActivity): DetailViewModelFactory {
        val repository = getDetailRepository(activity)
        return DetailViewModelFactory(repository, activity)
    }
}