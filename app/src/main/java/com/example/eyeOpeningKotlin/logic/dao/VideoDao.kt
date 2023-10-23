package com.example.eyeOpeningKotlin.logic.dao

import com.example.eyeOpeningKotlin.logic.model.VideoBeanForClient
import com.example.eyeOpeningKotlin.logic.model.VideoDetail
import com.example.eyeOpeningKotlin.logic.model.VideoRelated
import com.example.eyeOpeningKotlin.logic.model.VideoReplies

/**
 * 视频相关，对应的Dao操作类。
 *
 * @author driverSkr
 * @since  2023/10/20
 */
class VideoDao {

    fun cacheVideoDetail(bean: VideoDetail?) {
        //TODO("存储数据到本地")
    }

    fun getCachedVideoDetail(): VideoDetail? {
        TODO("获取本地存储的数据")
    }

    fun cacheVideoBeanForClient(bean: VideoBeanForClient?) {
        //TODO("存储数据到本地")
    }

    fun getCachedVideoBeanForClient(): VideoBeanForClient? {
        TODO("获取本地存储的数据")
    }

    fun cacheVideoRelated(bean: VideoRelated?) {
        //TODO("存储数据到本地")
    }

    fun getCachedVideoRelated(): VideoRelated? {
        TODO("获取本地存储的数据")
    }

    fun cacheVideoReplies(bean: VideoReplies?) {
        //TODO("存储数据到本地")
    }

    fun getCachedVideoReplies(): VideoReplies? {
        TODO("获取本地存储的数据")
    }
}