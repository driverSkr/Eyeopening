package com.example.eyeOpeningKotlin.ui.home.daily

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eyeOpeningKotlin.logic.MainPageRepository

class DailyViewModelFactory(private val repository: MainPageRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DailyViewModel(repository) as T
    }
}