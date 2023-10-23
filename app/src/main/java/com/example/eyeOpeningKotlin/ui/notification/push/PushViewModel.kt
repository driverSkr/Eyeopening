package com.example.eyeOpeningKotlin.ui.notification.push

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.eyeOpeningKotlin.logic.MainPageRepository
import com.example.eyeOpeningKotlin.logic.model.PushMessage
import kotlinx.coroutines.flow.Flow

class PushViewModel(private val repository: MainPageRepository) : ViewModel() {

    var dataList = ArrayList<PushMessage.Message>()

    fun getPagingData(): Flow<PagingData<PushMessage.Message>> {
        return repository.getMessagePagingData()
    }
}