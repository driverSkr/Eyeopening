package com.example.eye_openingKotlin.ui.notification.push

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.eye_openingKotlin.logic.MainPageRepository
import com.example.eye_openingKotlin.logic.model.PushMessage
import kotlinx.coroutines.flow.Flow

class PushViewModel(val repository: MainPageRepository) : ViewModel() {

    var dataList = ArrayList<PushMessage.Message>()

    fun getPagingData(): Flow<PagingData<PushMessage.Message>> {
        return repository.getMessagePagingData().cachedIn(viewModelScope)
    }
}
