package com.example.eyeOpeningKotlin.ui.ugcdetail

import android.app.Activity
import android.content.Intent
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.logic.model.CommunityRecommend
import com.example.eyeOpeningKotlin.ui.common.ui.BaseActivity
import com.example.eyeOpeningKotlin.util.IntentDataHolderUtil

/**
 * 社区-推荐详情页。
 *
 * @author driverSkr
 * @since  2023/10/23
 */
class UgcDetailActivity: BaseActivity() {

    companion object {

        const val TAG = "UgcDetailActivity"
        const val EXTRA_RECOMMEND_ITEM_LIST_JSON = "recommend_item_list"
        const val EXTRA_RECOMMEND_ITEM_JSON = "recommend_item"

        fun start(context: Activity, dataList: List<CommunityRecommend.Item>, currentItem: CommunityRecommend.Item) {
            IntentDataHolderUtil.setData(EXTRA_RECOMMEND_ITEM_LIST_JSON, dataList)
            IntentDataHolderUtil.setData(EXTRA_RECOMMEND_ITEM_JSON, currentItem)
            val starter = Intent(context, UgcDetailActivity::class.java)
            context.startActivity(starter)
            context.overridePendingTransition(R.anim.anl_push_bottom_in, R.anim.anl_push_up_out)
        }
    }
}