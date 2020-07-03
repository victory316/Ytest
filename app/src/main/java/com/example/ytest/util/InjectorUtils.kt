package com.example.ytest.util

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.ytest.data.AnswerRepository
import com.example.ytest.data.MainDatabase
import com.example.ytest.viewmodel.MainViewModelFactory

/**
 *
 *  InjectorUtils
 *
 *  - Database와 viewModel의 Injection을 돕는 class
 *
 */

object InjectorUtils {
    private fun getMainRepository(context: Context): AnswerRepository {
        return AnswerRepository.getInstance(
            MainDatabase.getInstance(context.applicationContext)!!.answerDao()
        )
    }

    fun provideMainViewModel(fragment: Fragment): MainViewModelFactory {
        val repository = getMainRepository(fragment.requireContext())
        return MainViewModelFactory(repository, fragment)
    }
}