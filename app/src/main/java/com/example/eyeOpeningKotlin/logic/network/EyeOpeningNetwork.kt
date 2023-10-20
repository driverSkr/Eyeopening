package com.example.eyeOpeningKotlin.logic.network

import com.example.eyeOpeningKotlin.logic.network.api.MainPageService
import com.example.eyeOpeningKotlin.logic.network.api.VideoService

/**
 * 管理所有网络请求。
 *
 * @author boge
 * @since  2023/10/20
 */
class EyeOpeningNetwork {

    var mainPageService = ServiceCreator.create(MainPageService::class.java)
        private set

    private val videoService = ServiceCreator.create(VideoService::class.java)

    suspend fun fetchHotSearch() = mainPageService.getHotSearch()

    suspend fun fetchVideoBeanForClient(videoId: Long) = videoService.getVideoBeanForClient(videoId)

    suspend fun fetchVideoRelated(videoId: Long) = videoService.getVideoRelated(videoId)

    suspend fun fetchVideoReplies(url: String) = videoService.getVideoReplies(url)

    companion object {

        @Volatile
        private var INSTANCE: EyeOpeningNetwork? = null

        fun getInstance(): EyeOpeningNetwork = INSTANCE ?: synchronized(this) {
            INSTANCE ?: EyeOpeningNetwork()
        }
    }
}