package com.example.eyeOpeningKotlin.logic

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.eyeOpeningKotlin.Const
import com.example.eyeOpeningKotlin.logic.dao.MainPageDao
import com.example.eyeOpeningKotlin.logic.model.CommunityRecommend
import com.example.eyeOpeningKotlin.logic.model.Follow
import com.example.eyeOpeningKotlin.logic.model.PushMessage
import com.example.eyeOpeningKotlin.logic.network.EyeOpeningNetwork
import com.example.eyeOpeningKotlin.ui.community.commend.CommendPagingSource
import com.example.eyeOpeningKotlin.ui.community.follow.FollowPagingSource
import com.example.eyeOpeningKotlin.ui.notification.push.PushPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow

/**
 * 主页界面，主要包含：（首页，社区，通知，我的），对应的仓库数据管理。
 *
 * @author driverSkr
 * @since  2023/10/20
 */
class MainPageRepository private constructor(private val mainPageDao: MainPageDao,private val eyeOpeningNetwork: EyeOpeningNetwork){

    suspend fun refreshHotSearch() = requestHotSearch()

    fun getMessagePagingData(): Flow<PagingData<PushMessage.Message>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { PushPagingSource(eyeOpeningNetwork.mainPageService) }
        ).flow
    }

    /**
     * 用于获取社区-推荐 数据的分页数据
     * @return Flow 对象，允许异步访问这些数据
     */
    fun getCommunityRecommendPagingData(): Flow<PagingData<CommunityRecommend.Item>> {
        /**
         * Pager(...)：这是 Paging 3 分页库的一部分，用于配置和创建分页数据源
         */
        return Pager(
            /**PagingConfig 接受分页的配置信息；Const.Config.PAGE_SIZE 表示每页的数据项数量*/
            config = PagingConfig(Const.Config.PAGE_SIZE),//50
            /**定义了分页数据源的工厂函数；pagingSourceFactory 接受一个 Lambda 表达式，该 Lambda 表达式返回一个新的 PagingSource 对象，用于从数据源中加载分页数据*/
            pagingSourceFactory = { CommendPagingSource(eyeOpeningNetwork.mainPageService) }
        ).flow /**.flow：这是一个 Kotlin 中的扩展属性，用于将 Pager 对象转换为 Flow 对象。
                  Flow 是 Kotlin 协程库中的一种数据流类型，用于支持异步操作*/
    }

    fun getFollowPagingData(): Flow<PagingData<Follow.Item>> {
        return Pager(
            config = PagingConfig(Const.Config.PAGE_SIZE),
            pagingSourceFactory = { FollowPagingSource(eyeOpeningNetwork.mainPageService) }
        ).flow
    }

    /**
     * 一个 Kotlin 中的挂起函数，用于执行涉及网络请求和数据库操作的异步操作
     * * 挂起函数需要在协程中调用，允许执行长时间运行的操作而不会阻塞主线程
     * */
    /**调用withContext()函数之后，会立即执行代码块中的代码，同时将外部协程挂起。当代码块中的代码全部执行完之后，会将最后一行的执行结果作为withContext()函数的返回值返回
     * - Dispatchers.IO表示会使用一种较高并发的线程策略*/
    private suspend fun requestHotSearch() = withContext(Dispatchers.IO) {
        /**用于从网络获取热门搜索数据*/
        val response = eyeOpeningNetwork.fetchHotSearch()
        /**将网络响应数据缓存到本地数据库*/
        mainPageDao.cacheHotSearch(response)
        /**返回网络请求的响应对象，以便调用方（协程）可以进一步处理响应数据*/
        response
    }

    companion object {

        @Volatile
        private var INSTANCE: MainPageRepository? = null

        fun getInstance(dao: MainPageDao, network: EyeOpeningNetwork): MainPageRepository = INSTANCE ?: synchronized(this) {
            INSTANCE ?: MainPageRepository(dao, network)
        }
    }
}