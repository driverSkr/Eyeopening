package com.example.eyeOpeningKotlin.ui.common.holder

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.AUTO_PLAY_VIDEO_AD
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.BANNER
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.BANNER3
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.COLUMN_CARD_LIST
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.FOLLOW_CARD
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.HORIZONTAL_SCROLL_CARD
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.INFORMATION_CARD
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.SPECIAL_SQUARE_CARD_COLLECTION
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.TAG_BRIEFCARD
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.TEXT_CARD_FOOTER2
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.TEXT_CARD_FOOTER3
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.TEXT_CARD_HEADER4
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.TEXT_CARD_HEADER5
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.TEXT_CARD_HEADER7
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.TEXT_CARD_HEADER8
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.TOPIC_BRIEFCARD
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.UGC_SELECTED_CARD_COLLECTION
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.UNKNOWN
import com.example.eyeOpeningKotlin.Const.ItemViewType.Companion.VIDEO_SMALL_CARD
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.extension.inflate
import com.example.eyeOpeningKotlin.logic.model.*
import com.example.eyeOpeningKotlin.ui.home.discovery.DiscoveryAdapter
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import com.zhpan.bannerview.BannerViewPager

/**
 * 项目通用ViewHolder，集中统一管理。
 *
 * @author driverSkr
 * @since  2023/10/18
 */

/**
 * 未知类型，占位进行容错处理。
 */
class EmptyViewHolder(view: View): RecyclerView.ViewHolder(view)

//相关推荐
class TextCardViewHeader4ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val tvTitle4: TextView = view.findViewById(R.id.tvTitle4)
    val ivInto4: ImageView = view.findViewById(R.id.ivInto4)
}

//五分钟新知
class TextCardViewHeader5ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle5: TextView = view.findViewById(R.id.tvTitle5)
    val tvFollow: TextView = view.findViewById(R.id.tvFollow)
    val ivInto5: ImageView = view.findViewById(R.id.ivInto5)
}

//开眼专栏
class TextCardViewHeader7ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle7: TextView = view.findViewById(R.id.tvTitle7)
    val tvRightText7: TextView = view.findViewById(R.id.tvRightText7)
    val ivInto7: ImageView = view.findViewById(R.id.ivInto7)
}

//本周榜单
class TextCardViewHeader8ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle8: TextView = view.findViewById(R.id.tvTitle8)
    val tvRightText8: TextView = view.findViewById(R.id.tvRightText8)
    val ivInto8: ImageView = view.findViewById(R.id.ivInto8)
}

//
class TextCardViewFooter2ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvFooterRightText2: TextView = view.findViewById(R.id.tvFooterRightText2)
    val ivTooterInto2: ImageView = view.findViewById(R.id.ivTooterInto2)
}

//刷新推荐
class TextCardViewFooter3ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvRefresh: TextView = view.findViewById(R.id.tvRefresh)
    val tvFooterRightText3: TextView = view.findViewById(R.id.tvFooterRightText3)
    val ivTooterInto3: ImageView = view.findViewById(R.id.ivTooterInto3)
}

class HorizontalScrollCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val bannerViewPager: BannerViewPager<Discovery.ItemX, DiscoveryAdapter.HorizontalScrollCardAdapter.ViewHolder> = view.findViewById(R.id.bannerViewPager)
}

class SpecialSquareCardCollectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
    val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    val tvRightText: TextView = view.findViewById(R.id.tvRightText)
    val ivInto: ImageView = view.findViewById(R.id.ivInto)
}

class ColumnCardListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
    val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    val tvRightText: TextView = view.findViewById(R.id.tvRightText)
    val ivInto: ImageView = view.findViewById(R.id.ivInto)
}

class FollowCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivVideo: ImageView = view.findViewById(R.id.ivVideo)
    val ivAvatar: ImageView = view.findViewById(R.id.ivAvatar)
    val tvVideoDuration: TextView = view.findViewById(R.id.tvVideoDuration)
    val tvDescription: TextView = view.findViewById(R.id.tvDescription)
    val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    val ivShare: ImageView = view.findViewById(R.id.ivShare)
    val tvLabel: TextView = view.findViewById(R.id.tvLabel)
    val ivChoiceness: ImageView = view.findViewById(R.id.ivChoiceness)
}

class Banner3ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivPicture: ImageView = view.findViewById(R.id.ivPicture)
    val tvLabel: TextView = view.findViewById(R.id.tvLabel)
    val ivAvatar: ImageView = view.findViewById(R.id.ivAvatar)
    val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    val tvDescription: TextView = view.findViewById(R.id.tvDescription)
}

class VideoSmallCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivPicture: ImageView = view.findViewById(R.id.ivPicture)
    val tvVideoDuration: TextView = view.findViewById(R.id.tvVideoDuration)
    val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    val tvDescription: TextView = view.findViewById(R.id.tvDescription)
    val ivShare: ImageView = view.findViewById(R.id.ivShare)
}

class TagBriefCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivPicture: ImageView = view.findViewById(R.id.ivPicture)
    val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    val tvDescription: TextView = view.findViewById(R.id.tvDescription)
    val tvFollow: TextView = view.findViewById(R.id.tvFollow)
}

class TopicBriefCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivPicture: ImageView = view.findViewById(R.id.ivPicture)
    val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    val tvDescription: TextView = view.findViewById(R.id.tvDescription)
}

class InformationCardFollowCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivCover: ImageView = view.findViewById(R.id.ivCover)
    val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
}

class AutoPlayVideoAdViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvLabel: TextView = view.findViewById(R.id.tvLabel)
    val ivAvatar: ImageView = view.findViewById(R.id.ivAvatar)
    val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    val tvDescription: TextView = view.findViewById(R.id.tvDescription)
    val videoPlayer: GSYVideoPlayer = view.findViewById(R.id.videoPlayer)
}

class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivPicture: ImageView = view.findViewById(R.id.ivPicture)
}

class UgcSelectedCardCollectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    val tvRightText: TextView = view.findViewById(R.id.tvRightText)
    val ivCoverLeft: ImageView = view.findViewById(R.id.ivCoverLeft)
    val ivLayersLeft: ImageView = view.findViewById(R.id.ivLayersLeft)
    val ivAvatarLeft: ImageView = view.findViewById(R.id.ivAvatarLeft)
    val tvNicknameLeft: TextView = view.findViewById(R.id.tvNicknameLeft)
    val ivCoverRightTop: ImageView = view.findViewById(R.id.ivCoverRightTop)
    val ivLayersRightTop: ImageView = view.findViewById(R.id.ivLayersRightTop)
    val ivAvatarRightTop: ImageView = view.findViewById(R.id.ivAvatarRightTop)
    val tvNicknameRightTop: TextView = view.findViewById(R.id.tvNicknameRightTop)
    val ivCoverRightBottom: ImageView = view.findViewById(R.id.ivCoverRightBottom)
    val ivLayersRightBottom: ImageView = view.findViewById(R.id.ivLayersRightBottom)
    val ivAvatarRightBottom: ImageView = view.findViewById(R.id.ivAvatarRightBottom)
    val tvNicknameRightBottom: TextView = view.findViewById(R.id.tvNicknameRightBottom)
}

/**
 * RecyclerView帮助类，获取通用ViewHolder或ItemViewType。
 */
object RecyclerViewHelp {

    fun getViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {

        TEXT_CARD_HEADER4 -> TextCardViewHeader4ViewHolder(R.layout.item_text_card_type_header_four.inflate(parent))

        TEXT_CARD_HEADER5 -> TextCardViewHeader5ViewHolder(R.layout.item_text_card_type_header_five.inflate(parent))

        TEXT_CARD_HEADER7 -> TextCardViewHeader7ViewHolder(R.layout.item_text_card_type_header_seven.inflate(parent))

        TEXT_CARD_HEADER8 -> TextCardViewHeader8ViewHolder(R.layout.item_text_card_type_header_eight.inflate(parent))

        TEXT_CARD_FOOTER2 -> TextCardViewFooter2ViewHolder(R.layout.item_text_card_type_footer_two.inflate(parent))

        TEXT_CARD_FOOTER3 -> TextCardViewFooter3ViewHolder(R.layout.item_text_card_type_footer_three.inflate(parent))

        HORIZONTAL_SCROLL_CARD -> HorizontalScrollCardViewHolder(R.layout.item_horizontal_scroll_card_type.inflate(parent))

        SPECIAL_SQUARE_CARD_COLLECTION -> SpecialSquareCardCollectionViewHolder(R.layout.item_special_square_card_collection_type.inflate(parent))

        COLUMN_CARD_LIST -> ColumnCardListViewHolder(R.layout.item_column_card_list_type.inflate(parent))

        BANNER -> BannerViewHolder(R.layout.item_banner_type.inflate(parent))

        BANNER3 -> Banner3ViewHolder(R.layout.item_banner_three_type.inflate(parent))

        VIDEO_SMALL_CARD -> VideoSmallCardViewHolder(R.layout.item_video_small_card_type.inflate(parent))

        TAG_BRIEFCARD -> TagBriefCardViewHolder(R.layout.item_brief_card_tag_brief_card_type.inflate(parent))

        TOPIC_BRIEFCARD -> TopicBriefCardViewHolder(R.layout.item_brief_card_topic_brief_card_type.inflate(parent))

        FOLLOW_CARD -> FollowCardViewHolder(R.layout.item_follow_card_type.inflate(parent))

        INFORMATION_CARD -> InformationCardFollowCardViewHolder(R.layout.item_information_card_type.inflate(parent))

        UGC_SELECTED_CARD_COLLECTION -> UgcSelectedCardCollectionViewHolder(R.layout.item_ugc_selected_card_collection_type.inflate(parent))

        AUTO_PLAY_VIDEO_AD -> AutoPlayVideoAdViewHolder(R.layout.item_auto_play_video_ad.inflate(parent))

        else -> EmptyViewHolder(View(parent.context))
    }

    fun getItemViewType(type: String, dataType: String) = when (type) {

        "horizontalScrollCard" -> {
            when (dataType) {
                "HorizontalScrollCard" -> HORIZONTAL_SCROLL_CARD
                else -> UNKNOWN
            }
        }
        "specialSquareCardCollection" -> {
            when (dataType) {
                "ItemCollection" -> SPECIAL_SQUARE_CARD_COLLECTION
                else -> UNKNOWN
            }
        }
        "columnCardList" -> {
            when (dataType) {
                "ItemCollection" -> COLUMN_CARD_LIST
                else -> UNKNOWN
            }
        }
        /*"textCard" -> {
            when (item.data.type) {
                "header5" -> TEXT_CARD_HEADER5
                "header7" -> TEXT_CARD_HEADER7
                "header8" -> TEXT_CARD_HEADER8
                "footer2" -> TEXT_CARD_FOOTER2
                "footer3" -> TEXT_CARD_FOOTER3
                else -> UNKNOWN
            }
        }*/
        "banner" -> {
            when (dataType) {
                "Banner" -> BANNER
                else -> UNKNOWN
            }
        }
        "banner3" -> {
            when (dataType) {
                "Banner" -> BANNER3
                else -> UNKNOWN
            }
        }
        "videoSmallCard" -> {
            when (dataType) {
                "VideoBeanForClient" -> VIDEO_SMALL_CARD
                else -> UNKNOWN
            }
        }
        "briefCard" -> {
            when (dataType) {
                "TagBriefCard" -> TAG_BRIEFCARD
                "TopicBriefCard" -> TOPIC_BRIEFCARD
                else -> UNKNOWN
            }
        }
        "followCard" -> {
            when (dataType) {
                "FollowCard" -> FOLLOW_CARD
                else -> UNKNOWN
            }
        }
        "informationCard" -> {
            when (dataType) {
                "InformationCard" -> INFORMATION_CARD
                else -> UNKNOWN
            }
        }
        "ugcSelectedCardCollection" -> {
            when (dataType) {
                "ItemCollection" -> UGC_SELECTED_CARD_COLLECTION
                else -> UNKNOWN
            }
        }
        "autoPlayVideoAd" -> {
            when (dataType) {
                "AutoPlayVideoAdDetail" -> AUTO_PLAY_VIDEO_AD
                else -> UNKNOWN
            }
        }
        else -> UNKNOWN
    }

    private fun getTextCardType(type: String) = when (type) {
        "header4" -> TEXT_CARD_HEADER4
        "header5" -> TEXT_CARD_HEADER5
        "header7" -> TEXT_CARD_HEADER7
        "header8" -> TEXT_CARD_HEADER8
        "footer2" -> TEXT_CARD_FOOTER2
        "footer3" -> TEXT_CARD_FOOTER3
        else -> UNKNOWN
    }

    fun getItemViewType(item: Discovery.Item): Int {
        return if (item.type == "textCard") getTextCardType(item.data.type) else getItemViewType(item.type, item.data.dataType)
    }

    fun getItemViewType(item: HomePageRecommend.Item): Int {
        return if (item.type == "textCard") getTextCardType(item.data.type) else getItemViewType(item.type, item.data.dataType)
    }

    fun getItemViewType(item: Daily.Item): Int {
        return if (item.type == "textCard") getTextCardType(item.data.type) else getItemViewType(item.type, item.data.dataType)
    }

    fun getItemViewType(item: Follow.Item): Int {
        return if (item.type == "textCard") getTextCardType(item.data.type) else getItemViewType(item.type, item.data.dataType)
    }

    fun getItemViewType(item: VideoRelated.Item): Int {
        return if (item.type == "textCard") getTextCardType(item.data.type) else getItemViewType(item.type, item.data.dataType)
    }

    fun getItemViewType(item: VideoReplies.Item): Int {
        return if (item.type == "textCard") getTextCardType(item.data.type) else getItemViewType(item.type, item.data.dataType)
    }

}