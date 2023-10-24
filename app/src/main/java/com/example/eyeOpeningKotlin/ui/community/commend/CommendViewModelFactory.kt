package com.example.eyeOpeningKotlin.ui.community.commend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eyeOpeningKotlin.logic.MainPageRepository

/**
 * 社区工厂，实现给viewModel传递参数
 * @param repository : 主页界面数据仓库
 */
class CommendViewModelFactory(private val repository: MainPageRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommendViewModel(repository) as T
    }
}