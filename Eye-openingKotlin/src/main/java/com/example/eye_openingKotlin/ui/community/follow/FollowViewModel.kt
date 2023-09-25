package com.example.eye_openingKotlin.ui.community.follow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.eye_openingKotlin.logic.MainPageRepository
import com.example.eye_openingKotlin.logic.model.Follow
import kotlinx.coroutines.flow.Flow

class FollowViewModel(val repository: MainPageRepository) : ViewModel() {

    var dataList = ArrayList<Follow.Item>()

    fun getPagingData(): Flow<PagingData<Follow.Item>> {
        return repository.getFollowPagingData().cachedIn(viewModelScope)
    }
}