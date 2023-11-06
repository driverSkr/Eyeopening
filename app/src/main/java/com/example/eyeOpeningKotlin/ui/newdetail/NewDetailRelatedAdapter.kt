package com.example.eyeOpeningKotlin.ui.newdetail

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.eyeOpeningKotlin.BuildConfig
import com.example.eyeOpeningKotlin.Const
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.extension.*
import com.example.eyeOpeningKotlin.logic.model.VideoRelated
import com.example.eyeOpeningKotlin.ui.common.holder.RecyclerViewHelp
import com.example.eyeOpeningKotlin.ui.common.holder.TextCardViewHeader4ViewHolder
import com.example.eyeOpeningKotlin.ui.common.holder.VideoSmallCardViewHolder
import com.example.eyeOpeningKotlin.ui.home.daily.DailyAdapter
import com.example.eyeOpeningKotlin.ui.login.LoginActivity

/**
 * 视频详情-相关推荐列表绑定的适配器
 *
 * @author driverSkr
 * @since  2023/10/25
 */
class NewDetailRelatedAdapter(private val activity: NewDetailActivity, val dataList: List<VideoRelated.Item>, private var videoInfoData: NewDetailActivity.VideoInfo?):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = dataList.size + 1

    override fun getItemViewType(position: Int) = when {
        position == 0 -> Const.ItemViewType.CUSTOM_HEADER
        dataList[position - 1].type == "simpleHotReplyScrollCard" && dataList[position - 1].data.dataType == "ItemCollection" -> SIMPLE_HOT_REPLY_CARD_TYPE
        else -> RecyclerViewHelp.getItemViewType(dataList[position - 1])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        Const.ItemViewType.CUSTOM_HEADER -> CustomHeaderViewHolder(R.layout.item_new_detail_custom_header_type.inflate(parent))
        SIMPLE_HOT_REPLY_CARD_TYPE -> SimpleHotReplyCardViewHolder(View(parent.context))
        else -> RecyclerViewHelp.getViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CustomHeaderViewHolder -> {
                videoInfoData?.let {
                    holder.run {
                        groupAuthor.gone()
                        tvTitle.text = videoInfoData?.title
                        tvCategory.text =
                            if (videoInfoData?.library == DailyAdapter.DAILY_LIBRARY_TYPE) "#${videoInfoData?.category} / 开眼精选" else "#${videoInfoData?.category}"
                        tvDescription.text = videoInfoData?.description
                        tvCollectionCount.text = videoInfoData?.consumption?.collectionCount.toString()
                        tvShareCount.text = videoInfoData?.consumption?.shareCount.toString()
                        videoInfoData?.author?.run {
                            groupAuthor.visible()
                            ivAvatar.load(videoInfoData?.author?.icon ?: "")
                            tvAuthorDescription.text = videoInfoData?.author?.description
                            tvAuthorName.text = videoInfoData?.author?.name
                        }
                        setOnClickListener(ivCollectionCount, tvCollectionCount, ivShare, tvShareCount, ivCache, tvCache, ivFavorites, tvFavorites, tvFollow) {
                            when (this) {
                                ivCollectionCount, tvCollectionCount, ivFavorites, tvFavorites -> LoginActivity.start(activity)
                                ivShare, tvShareCount -> showDialogShare(activity, "${videoInfoData?.title}：${videoInfoData?.webUrl?.raw}")
                                ivCache, tvCache -> R.string.currently_not_supported.showToast()
                                tvFollow -> LoginActivity.start(activity)
                            }
                        }
                    }
                }
            }
            is SimpleHotReplyCardViewHolder -> {
                // 不做任何处理，忽略此ViewHolder。
            }
            is TextCardViewHeader4ViewHolder -> {
                val item = dataList[position - 1]
                holder.tvTitle4.text = item.data.text
            }
            is VideoSmallCardViewHolder -> {
                val item = dataList[position - 1]
                holder.ivPicture.load(item.data.cover.feed, 4f)
                holder.tvDescription.text = if (item.data.library == DailyAdapter.DAILY_LIBRARY_TYPE) "#${item.data.category} / 开眼精选" else "#${item.data.category}"
                holder.tvDescription.setTextColor(ContextCompat.getColor(activity, R.color.whiteAlpha35))
                holder.tvTitle.text = item.data.title
                holder.tvTitle.setTextColor(ContextCompat.getColor(activity, R.color.white))
                holder.tvVideoDuration.text = item.data.duration.conversionVideoDuration()
                holder.ivShare.setOnClickListener { showDialogShare(activity, "${item.data.title}：${item.data.webUrl.raw}") }
                holder.itemView.setOnClickListener {
                    item.data.run {
                        NewDetailActivity.start(
                            activity,
                            NewDetailActivity.VideoInfo(id, playUrl, title, description, category, library, consumption, cover, author, webUrl)
                        )
                        activity.scrollTop()
                    }
                }
            }
            else -> {
                holder.itemView.gone()
                if (BuildConfig.DEBUG) "${TAG}:${Const.Toast.BIND_VIEWHOLDER_TYPE_WARN}\n${holder}".showToast()
            }
        }
    }

    fun bindVideoInfo(videoInfoData: NewDetailActivity.VideoInfo?) {
        this.videoInfoData = videoInfoData
    }

    class CustomHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvCategory: TextView = view.findViewById(R.id.tvCategory)
        //val ivFoldText: ImageView = view.findViewById(R.id.ivFoldText)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val ivCollectionCount: ImageView = view.findViewById(R.id.ivCollectionCount)
        val tvCollectionCount: TextView = view.findViewById(R.id.tvCollectionCount)
        val ivShare: ImageView = view.findViewById(R.id.ivShare)
        val tvShareCount: TextView = view.findViewById(R.id.tvShareCount)
        val ivCache: ImageView = view.findViewById(R.id.ivCache)
        val tvCache: TextView = view.findViewById(R.id.tvCache)
        val ivFavorites: ImageView = view.findViewById(R.id.ivFavorites)
        val tvFavorites: TextView = view.findViewById(R.id.tvFavorites)
        val ivAvatar: ImageView = view.findViewById(R.id.ivAvatar)
        val tvAuthorDescription: TextView = view.findViewById(R.id.tvAuthorDescription)
        val tvAuthorName: TextView = view.findViewById(R.id.tvAuthorName)
        val tvFollow: TextView = view.findViewById(R.id.tvFollow)
        val groupAuthor: Group = view.findViewById(R.id.groupAuthor)
    }

    /**
     * 相关推荐，数据集合里附带的热门评论，UI展示上不做处理。
     */
    class SimpleHotReplyCardViewHolder(view: View) : RecyclerView.ViewHolder(view)


    companion object {
        const val TAG = "NewDetailRelatedAdapter"
        const val SIMPLE_HOT_REPLY_CARD_TYPE = Const.ItemViewType.MAX
    }
}