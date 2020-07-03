package com.example.ytest.viewmodel

import androidx.lifecycle.*
import com.example.ytest.data.AnswerRepository

class MainViewModel internal constructor(
    private val repository: AnswerRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

}