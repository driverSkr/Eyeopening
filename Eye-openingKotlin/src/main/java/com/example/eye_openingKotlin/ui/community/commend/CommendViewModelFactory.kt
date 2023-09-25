package com.example.eye_openingKotlin.ui.community.commend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eye_openingKotlin.logic.MainPageRepository

class CommendViewModelFactory(private val repository: MainPageRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommendViewModel(repository) as T
    }
}