package com.example.eyeOpeningKotlin.ui.notification.push

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eyeOpeningKotlin.logic.MainPageRepository

class PushViewModelFactory(private val repository: MainPageRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PushViewModel(repository) as T
    }
}