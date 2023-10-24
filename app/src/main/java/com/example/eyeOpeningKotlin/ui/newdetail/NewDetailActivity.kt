package com.example.eyeOpeningKotlin.ui.newdetail

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.logic.model.Author
import com.example.eyeOpeningKotlin.logic.model.Consumption
import com.example.eyeOpeningKotlin.logic.model.Cover
import com.example.eyeOpeningKotlin.logic.model.WebUrl
import kotlinx.parcelize.Parcelize

class NewDetailActivity {

    private var _binding: ActivityNewDetailBinding? = null

    private val binding: ActivityNewDetailBinding
        get() = _binding!!

    @Parcelize
    data class VideoInfo(
        val videoId: Long, val playUrl: String, val title: String, val description: String, val category: String, val library: String,
        val consumption: Consumption, val cover: Cover, val author: Author?, val webUrl: WebUrl
    ) : Parcelable

    companion object {

        const val TAG = "NewDetailActivity"
        const val EXTRA_VIDEOINFO = "videoInfo"
        const val EXTRA_VIDEO_ID = "videoId"

        fun start(context: Activity, videoInfo: VideoInfo) {
            val starter = Intent(context, NewDetailActivity::class.java)
            starter.putExtra(EXTRA_VIDEOINFO, videoInfo)
            context.startActivity(starter)
            context.overridePendingTransition(R.anim.anl_push_bottom_in, R.anim.anl_push_up_out)
        }

        fun start(context: Activity, videoId: Long) {
            val starter = Intent(context, NewDetailActivity::class.java)
            starter.putExtra(EXTRA_VIDEO_ID, videoId)
            context.startActivity(starter)
            context.overridePendingTransition(R.anim.anl_push_bottom_in, R.anim.anl_push_up_out)
        }
    }
}