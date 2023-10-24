package com.example.eyeOpeningKotlin.ui.community.commend

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.eyeOpeningKotlin.logic.model.CommunityRecommend
import com.example.eyeOpeningKotlin.logic.network.api.MainPageService

/**
 * Paging 3 分页库的一部分，用于加载分页数据
 * 实现了 PagingSource 接口，该接口是 Paging 3 中用于分页加载数据的核心部分
 */
class CommendPagingSource(private val mainPageService: MainPageService) : PagingSource<String, CommunityRecommend.Item>() /**并且每一页都与一个字符串键 String 相关联**/ {

    /**
     * 用于加载一页数据
     * @param ：包含加载参数和键
     */
    override suspend fun load(params: LoadParams<String>): LoadResult<String, CommunityRecommend.Item> {
        return try {
            /**获取URL*/
            val page = params.key ?: MainPageService.COMMUNITY_RECOMMEND_URL
            /**使用 mainPageService 执行网络请求,获取社区-推荐数据的响应*/
            val repoResponse = mainPageService.getCommunityRecommend(page)
            /**获取社区-推荐项目的列表，这个列表是要返回的一页数据*/
            val repoItems = repoResponse.itemList
            val prevKey = null
            /**判断是否有下一页数据，如果有，将下一页的 URL 作为 nextKey，否则将 nextKey 设置为 null*/
            val nextKey = if (repoItems.isNotEmpty() && !repoResponse.nextPageUrl.isNullOrEmpty()) repoResponse.nextPageUrl else null
            /**使用 LoadResult.Page 创建一个包含分页数据的 LoadResult 对象，
             * repoItems 作为当前页的数据，prevKey 设置为 null，以及 nextKey 包含下一页的键*/
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    /**
     * 用于获取刷新键。在这里，它返回 null，表示不支持刷新操作
     * 通常，刷新键用于加载前一页数据，但在这里，它被设置为 null，表明不支持刷新操作
     */
    override fun getRefreshKey(state: PagingState<String, CommunityRecommend.Item>): String? = null
}