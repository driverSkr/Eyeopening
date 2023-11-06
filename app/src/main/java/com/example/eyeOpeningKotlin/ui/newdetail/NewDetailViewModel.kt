package com.example.eyeOpeningKotlin.ui.newdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.eyeOpeningKotlin.logic.VideoRepository
import com.example.eyeOpeningKotlin.logic.model.VideoRelated
import com.example.eyeOpeningKotlin.logic.model.VideoReplies
import com.example.eyeOpeningKotlin.logic.network.api.VideoService

/**
 * 视频详情界面绑定的ViewModel。
 *
 * @author driverSkr
 * @since  2023/10/25
 */
class NewDetailViewModel(repository: VideoRepository) : ViewModel() {

    var relatedDataList = ArrayList<VideoRelated.Item>()

    var repliesDataList = ArrayList<VideoReplies.Item>()

    var videoInfoData: NewDetailActivity.VideoInfo? = null

    var videoId: Long = 0L

    private var _repliesLiveData = MutableLiveData<String>()
    private var _videoDetailLiveData = MutableLiveData<RequestParam>()
    private var _repliesAndRepliesLiveData = MutableLiveData<RequestParam>()

    var nextPageUrl: String? = null

    val videoDetailLiveData = Transformations.switchMap(_videoDetailLiveData) {
        liveData {
            val result = try {
                val videoDetail = repository.refreshVideoDetail(it.videoId, it.repliesUrl)   //视频信息+相关推荐+评论
                Result.success(videoDetail)
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
    }

    val repliesAndRepliesLiveData = Transformations.switchMap(_repliesAndRepliesLiveData) {
        liveData {
            val result = try {
                val videoDetail = repository.refreshVideoRelatedAndVideoReplies(it.videoId, it.repliesUrl)   //相关推荐+评论
                Result.success(videoDetail)
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
    }

    val repliesLiveData = Transformations.switchMap(_repliesLiveData) {
        liveData {
            val result = try {
                val videoDetail = repository.refreshVideoReplies(it)   //评论
                Result.success(videoDetail)
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }
    }

    fun onRefresh() {
        if (videoInfoData == null) {
            _videoDetailLiveData.value = RequestParam(videoId, "${VideoService.VIDEO_REPLIES_URL}$videoId")
        } else {
            _repliesAndRepliesLiveData.value = RequestParam(videoInfoData?.videoId ?: 0L, "${VideoService.VIDEO_REPLIES_URL}${videoInfoData?.videoId ?: 0L}")
        }
    }

    fun onLoadMore() {
        _repliesLiveData.value = nextPageUrl ?: ""
    }

    inner class RequestParam(val videoId: Long, val repliesUrl: String)
}