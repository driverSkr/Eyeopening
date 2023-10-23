package com.example.eyeOpeningKotlin.ui.community.commend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.eyeOpeningKotlin.logic.MainPageRepository
import com.example.eyeOpeningKotlin.logic.model.CommunityRecommend
import kotlinx.coroutines.flow.Flow

class CommendViewModel(val repository: MainPageRepository): ViewModel() {

    var dataList = ArrayList<CommunityRecommend.Item>()

    fun getPagingData(): Flow<PagingData<CommunityRecommend.Item>> {
        return repository.getCommunityRecommendPagingData().cachedIn(viewModelScope)
    }
}