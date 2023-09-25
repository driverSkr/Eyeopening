package com.example.eye_openingKotlin.logic.network

import com.example.eye_openingKotlin.logic.network.api.MainPageService
import com.example.eye_openingKotlin.logic.network.api.VideoService

/**
 * 管理所有网络请求。
 *
 * @author vipyinzhiwei
 * @since  2020/5/2
 */
class EyepetizerNetwork {

    var mainPageService = ServiceCreator.create(MainPageService::class.java)
        private set

    private val videoService = ServiceCreator.create(VideoService::class.java)

    suspend fun fetchHotSearch() = mainPageService.getHotSearch()

    suspend fun fetchVideoBeanForClient(videoId: Long) = videoService.getVideoBeanForClient(videoId)

    suspend fun fetchVideoRelated(videoId: Long) = videoService.getVideoRelated(videoId)

    suspend fun fetchVideoReplies(url: String) = videoService.getVideoReplies(url)

    companion object {

        @Volatile
        private var INSTANCE: EyepetizerNetwork? = null

        fun getInstance(): EyepetizerNetwork = INSTANCE ?: synchronized(this) {
            INSTANCE ?: EyepetizerNetwork()
        }
    }
}