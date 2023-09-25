package com.example.eye_openingKotlin.logic.network.api

import com.example.eye_openingKotlin.logic.model.VideoBeanForClient
import com.example.eye_openingKotlin.logic.model.VideoRelated
import com.example.eye_openingKotlin.logic.model.VideoReplies
import com.example.eye_openingKotlin.logic.network.ServiceCreator
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * 与视频页面相关API请求。
 *
 * @author vipyinzhiwei
 * @since  2020/5/14
 */
interface VideoService {

    /**
     * 视频详情-视频信息
     */
    @GET("api/v2/video/{id}")
    suspend fun getVideoBeanForClient(@Path("id") videoId: Long): VideoBeanForClient

    /**
     * 视频详情-推荐列表
     */
    @GET("api/v4/video/related")
    suspend fun getVideoRelated(@Query("id") videoId: Long): VideoRelated

    /**
     * 视频详情-评论列表
     */
    @GET
    suspend fun getVideoReplies(@Url url: String): VideoReplies

    companion object {

        /**
         * 视频详情-评论列表URL
         */
        const val VIDEO_REPLIES_URL = "${ServiceCreator.BASE_URL}api/v2/replies/video?videoId="
    }

}