package com.example.eye_openingKotlin.ui.community.commend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.eye_openingKotlin.logic.MainPageRepository
import com.example.eye_openingKotlin.logic.model.CommunityRecommend
import kotlinx.coroutines.flow.Flow

class CommendViewModel(val repository: MainPageRepository) : ViewModel() {

    var dataList = ArrayList<CommunityRecommend.Item>()

    fun getPagingData(): Flow<PagingData<CommunityRecommend.Item>> {
        return repository.getCommunityRecommendPagingData().cachedIn(viewModelScope)
    }
}