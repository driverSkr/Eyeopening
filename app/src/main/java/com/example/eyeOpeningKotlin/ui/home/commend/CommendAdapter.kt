package com.example.eyeOpeningKotlin.ui.home.commend

import android.app.Activity
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eyeOpeningKotlin.extension.load
import com.example.eyeOpeningKotlin.logic.model.HomePageRecommend
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer

class CommendAdapter(val fragment: CommendFragment) : PagingDataAdapter<HomePageRecommend.Item, RecyclerView.ViewHolder>(DIFF_CALLBACK)  {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    companion object {

        const val TAG = "CommendAdapter"

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HomePageRecommend.Item>() {

            override fun areItemsTheSame(oldItem: HomePageRecommend.Item, newItem: HomePageRecommend.Item) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: HomePageRecommend.Item, newItem: HomePageRecommend.Item) = oldItem == newItem
        }

        fun startAutoPlay(
            activity: Activity,
            player: GSYVideoPlayer,
            position: Int,
            playUrl: String,
            coverUrl: String,
            playTag: String,
            callBack: GSYSampleCallBack? = null
        ) {
            player.run {
                //防止错位设置
                setPlayTag(playTag)
                //设置播放位置防止错位
                setPlayPosition(position)
                //音频焦点冲突时是否释放
                setReleaseWhenLossAudio(false)
                //设置循环播放
                setLooping(true)
                //增加封面
                val cover = ImageView(activity)
                cover.scaleType = ImageView.ScaleType.CENTER_CROP
                cover.load(coverUrl, 4f)
                cover.parent?.run { removeView(cover) }
                setThumbImageView(cover)
                //设置播放过程中的回调
                setVideoAllCallBack(callBack)
                //设置播放URL
                setUp(playUrl, false, null)
            }
        }
    }
}