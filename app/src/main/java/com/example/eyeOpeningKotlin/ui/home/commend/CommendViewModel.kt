package com.example.eyeOpeningKotlin.ui.home.commend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.eyeOpeningKotlin.logic.MainPageRepository
import com.example.eyeOpeningKotlin.logic.model.HomePageRecommend
import kotlinx.coroutines.flow.Flow

class CommendViewModel(val repository: MainPageRepository) : ViewModel() {

    var dataList = ArrayList<HomePageRecommend.Item>()

    fun getPagingData(): Flow<PagingData<HomePageRecommend.Item>> {
        return repository.getHomePageRecommendPagingData().cachedIn(viewModelScope)
    }
}
