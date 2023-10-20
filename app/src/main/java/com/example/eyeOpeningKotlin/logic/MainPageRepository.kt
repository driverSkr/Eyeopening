package com.example.eyeOpeningKotlin.logic

import com.example.eyeOpeningKotlin.logic.dao.MainPageDao
import com.example.eyeOpeningKotlin.logic.network.EyeOpeningNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.Flow

/**
 * 主页界面，主要包含：（首页，社区，通知，我的），对应的仓库数据管理。
 *
 * @author boge
 * @since  2023/10/20
 */
class MainPageRepository private constructor(private val mainPageDao: MainPageDao,private val eyeOpeningNetwork: EyeOpeningNetwork){

    suspend fun refreshHotSearch() = requestHotSearch()

    //fun getMessagePagingData(): Flow<P>

    private suspend fun requestHotSearch() = withContext(Dispatchers.IO) {
        val response = eyeOpeningNetwork.fetchHotSearch()
        mainPageDao.cacheHotSearch(response)
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