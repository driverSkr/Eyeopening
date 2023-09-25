package com.example.eye_openingKotlin.logic

import com.example.eye_openingKotlin.logic.dao.VideoDao
import com.example.eye_openingKotlin.logic.model.VideoDetail
import com.example.eye_openingKotlin.logic.network.EyepetizerNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * 视频相关，对应的仓库数据管理。
 *
 * @author vipyinzhiwei
 * @since  2020/5/15
 */
class VideoRepository(private val dao: VideoDao, private val network: EyepetizerNetwork) {

    suspend fun refreshVideoReplies(repliesUrl: String) = requestVideoReplies(repliesUrl)

    suspend fun refreshVideoRelatedAndVideoReplies(videoId: Long, repliesUrl: String) = requestVideoRelatedAndVideoReplies(videoId, repliesUrl)

    suspend fun refreshVideoDetail(videoId: Long, repliesUrl: String) = requestVideoDetail(videoId, repliesUrl)

    private suspend fun requestVideoReplies(url: String) = withContext(Dispatchers.IO) {
        val deferredVideoReplies = async { network.fetchVideoReplies(url) }
        val videoReplies = deferredVideoReplies.await()
        videoReplies
    }

    private suspend fun requestVideoRelatedAndVideoReplies(videoId: Long, repliesUrl: String) = withContext(Dispatchers.IO) {
        val deferredVideoRelated = async { network.fetchVideoRelated(videoId) }
        val deferredVideoReplies = async { network.fetchVideoReplies(repliesUrl) }
        val videoRelated = deferredVideoRelated.await()
        val videoReplies = deferredVideoReplies.await()
        val videoDetail = VideoDetail(null, videoRelated, videoReplies)
        videoDetail
    }

    private suspend fun requestVideoDetail(videoId: Long, repliesUrl: String) = withContext(Dispatchers.IO) {
        val deferredVideoRelated = async { network.fetchVideoRelated(videoId) }
        val deferredVideoReplies = async { network.fetchVideoReplies(repliesUrl) }
        val deferredVideoBeanForClient = async { network.fetchVideoBeanForClient(videoId) }
        val videoBeanForClient = deferredVideoBeanForClient.await()
        val videoRelated = deferredVideoRelated.await()
        val videoReplies = deferredVideoReplies.await()
        val videoDetail = VideoDetail(videoBeanForClient, videoRelated, videoReplies)
        dao.cacheVideoDetail(videoDetail)
        videoDetail
    }

    companion object {

        @Volatile
        private var INSTANCE: VideoRepository? = null

        fun getInstance(dao: VideoDao, network: EyepetizerNetwork): VideoRepository = INSTANCE ?: synchronized(this) {
            INSTANCE ?: VideoRepository(dao, network)
        }
    }

}