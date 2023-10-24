package com.example.eyeOpeningKotlin.ui.community.follow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.eyeOpeningKotlin.logic.MainPageRepository
import com.example.eyeOpeningKotlin.logic.model.Follow
import kotlinx.coroutines.flow.Flow

class FollowViewModel(private val repository: MainPageRepository): ViewModel() {

    var dataList = ArrayList<Follow.Item>()

    fun getPagingData(): Flow<PagingData<Follow.Item>> {
        return repository.getFollowPagingData().cachedIn(viewModelScope)
    }
}