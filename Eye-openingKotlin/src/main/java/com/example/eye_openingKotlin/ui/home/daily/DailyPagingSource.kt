package com.example.eye_openingKotlin.ui.home.daily

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.eye_openingKotlin.logic.model.Daily
import com.example.eye_openingKotlin.logic.network.api.MainPageService

class DailyPagingSource(private val mainPageService: MainPageService) : PagingSource<String, Daily.Item>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Daily.Item> {
        return try {
            val page = params.key ?: MainPageService.DAILY_URL
            val repoResponse = mainPageService.getDaily(page)
            val repoItems = repoResponse.itemList
            val prevKey = null
            val nextKey = if (repoItems.isNotEmpty() && !repoResponse.nextPageUrl.isNullOrEmpty()) repoResponse.nextPageUrl else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Daily.Item>): String? = null
}