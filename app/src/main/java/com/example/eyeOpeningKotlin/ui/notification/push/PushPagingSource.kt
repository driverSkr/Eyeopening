package com.example.eyeOpeningKotlin.ui.notification.push

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.eyeOpeningKotlin.EyeopeningApplication
import com.example.eyeOpeningKotlin.logic.model.PushMessage
import com.example.eyeOpeningKotlin.logic.network.api.MainPageService

class PushPagingSource(val mainPageService: MainPageService): PagingSource<String,PushMessage.Message>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PushMessage.Message> {
        return try {
            val page = params.key ?: MainPageService.PUSH_MESSAGE_URL
            Log.d(EyeopeningApplication.name,"路径：$page")
            val repoResponse = mainPageService.getPushMessage(page)
            Log.d(EyeopeningApplication.name,"响应数据：：$repoResponse")
            val repoItems = repoResponse.itemList
            val prevKey = null
            val nextKey = if (repoItems.isNotEmpty() && !repoResponse.nextPageUrl.isNullOrEmpty()) repoResponse.nextPageUrl else null
            LoadResult.Page(repoItems,prevKey,nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, PushMessage.Message>): String? = null
}