package com.example.eye_openingKotlin.ui.community.commend

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.eye_openingKotlin.logic.model.CommunityRecommend
import com.example.eye_openingKotlin.logic.network.api.MainPageService

class CommendPagingSource(private val mainPageService: MainPageService) : PagingSource<String, CommunityRecommend.Item>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, CommunityRecommend.Item> {
        return try {
            val page = params.key ?: MainPageService.COMMUNITY_RECOMMEND_URL
            val repoResponse = mainPageService.getCommunityRecommend(page)
            val repoItems = repoResponse.itemList
            val prevKey = null
            val nextKey = if (repoItems.isNotEmpty() && !repoResponse.nextPageUrl.isNullOrEmpty()) repoResponse.nextPageUrl else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, CommunityRecommend.Item>): String? = null
}