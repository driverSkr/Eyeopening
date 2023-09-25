package com.example.eye_openingKotlin.logic

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.eye_openingKotlin.Const
import com.example.eye_openingKotlin.logic.dao.MainPageDao
import com.example.eye_openingKotlin.logic.model.*
import com.example.eye_openingKotlin.logic.network.EyepetizerNetwork
import com.example.eye_openingKotlin.ui.community.commend.CommendPagingSource
import com.example.eye_openingKotlin.ui.community.follow.FollowPagingSource
import com.example.eye_openingKotlin.ui.home.daily.DailyPagingSource
import com.example.eye_openingKotlin.ui.home.discovery.DiscoveryPagingSource
import com.example.eye_openingKotlin.ui.notification.push.PushPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * 主页界面，主要包含：（首页，社区，通知，我的），对应的仓库数据管理。
 *
 * @author vipyinzhiwei
 * @since  2020/5/2
 */
class MainPageRepository private constructor(private val mainPageDao: MainPageDao, private val eyepetizerNetwork: EyepetizerNetwork) {

    suspend fun refreshHotSearch() = requestHotSearch()

    fun getMessagePagingData(): Flow<PagingData<PushMessage.Message>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { PushPagingSource(eyepetizerNetwork.mainPageService) }
        ).flow
    }

    fun getCommunityRecommendPagingData(): Flow<PagingData<CommunityRecommend.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { CommendPagingSource(eyepetizerNetwork.mainPageService) }
        ).flow
    }

    fun getFollowPagingData(): Flow<PagingData<Follow.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { FollowPagingSource(eyepetizerNetwork.mainPageService) }
        ).flow
    }

    fun getDiscoveryPagingData(): Flow<PagingData<Discovery.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { DiscoveryPagingSource(eyepetizerNetwork.mainPageService) }
        ).flow
    }

    fun getHomePageRecommendPagingData(): Flow<PagingData<HomePageRecommend.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { com.example.eye_openingKotlin.ui.home.commend.CommendPagingSource(eyepetizerNetwork.mainPageService) }
        ).flow
    }

    fun getDailyPagingData(): Flow<PagingData<Daily.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { DailyPagingSource(eyepetizerNetwork.mainPageService) }
        ).flow
    }

    private suspend fun requestHotSearch() = withContext(Dispatchers.IO) {
        val response = eyepetizerNetwork.fetchHotSearch()
        mainPageDao.cacheHotSearch(response)
        response
    }

    companion object {

        @Volatile
        private var INSTANCE: MainPageRepository? = null

        fun getInstance(dao: MainPageDao, network: EyepetizerNetwork): MainPageRepository = INSTANCE ?: synchronized(this) {
            INSTANCE ?: MainPageRepository(dao, network)
        }
    }
}