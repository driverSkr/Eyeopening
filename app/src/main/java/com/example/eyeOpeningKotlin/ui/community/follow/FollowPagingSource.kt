package com.example.eyeOpeningKotlin.ui.community.follow

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.eyeOpeningKotlin.logic.model.Follow
import com.example.eyeOpeningKotlin.logic.network.api.MainPageService

class FollowPagingSource(private val mainPageService: MainPageService) : PagingSource<String, Follow.Item>() {

    override fun getRefreshKey(state: PagingState<String, Follow.Item>): String? = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Follow.Item> {
        return try {
            val page = params.key ?: MainPageService.FOLLOW_URL
            val repoResponse = mainPageService.getFollow(page)
            val repoItems = repoResponse.itemList
            val prevKey = null
            val nextKey = if (repoItems.isNotEmpty() && !repoResponse.nextPageUrl.isNullOrEmpty()) repoResponse.nextPageUrl else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}