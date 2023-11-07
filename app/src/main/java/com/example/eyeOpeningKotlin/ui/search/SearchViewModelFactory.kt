package com.example.eyeOpeningKotlin.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eyeOpeningKotlin.logic.MainPageRepository

/**
 * @codeReview 2023/11/7
 */
class SearchViewModelFactory(private val repository: MainPageRepository) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}