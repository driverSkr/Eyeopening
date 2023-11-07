package com.example.eyeOpeningKotlin.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.eyeOpeningKotlin.logic.MainPageRepository

/**
 * @codeReview 2023/11/7
 */
class SearchViewModel(private val repository: MainPageRepository): ViewModel() {

    var dataList = ArrayList<String>()

    private var requestParamLiveData = MutableLiveData<Any>()

    val dataListLiveData = Transformations.switchMap(requestParamLiveData) {
        liveData {
            val result = try {
                val hotSearch = repository.refreshHotSearch()
                Result.success(hotSearch)
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
    }

    fun onRefresh() {
        requestParamLiveData.value = requestParamLiveData.value
    }
}