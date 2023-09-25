package com.example.eye_openingKotlin.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eye_openingKotlin.R
import com.example.eye_openingKotlin.event.MessageEvent
import com.example.eye_openingKotlin.event.RefreshEvent
import com.example.eye_openingKotlin.event.SwitchPagesEvent
import com.example.eye_openingKotlin.logic.model.TabEntity
import com.example.eye_openingKotlin.ui.common.ui.BaseViewPagerFragment
import com.example.eye_openingKotlin.ui.community.commend.CommendFragment
import com.example.eye_openingKotlin.ui.community.follow.FollowFragment
import com.example.eye_openingKotlin.util.GlobalUtil
import com.flyco.tablayout.listener.CustomTabEntity
import org.greenrobot.eventbus.EventBus

/**
 * 社区主界面。
 *
 * @author vipyinzhiwei
 * @since  2020/5/1
 */
class CommunityFragment : BaseViewPagerFragment() {

    override val createTitles = ArrayList<CustomTabEntity>().apply {
        add(TabEntity(GlobalUtil.getString(R.string.commend)))
        add(TabEntity(GlobalUtil.getString(R.string.follow)))
    }

    override val createFragments: Array<Fragment> = arrayOf(CommendFragment.newInstance(), FollowFragment.newInstance())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater.inflate(R.layout.fragment_main_container, container, false))
    }

    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)
        if (messageEvent is RefreshEvent && this::class.java == messageEvent.activityClass) {
            when (viewPager?.currentItem) {
                0 -> EventBus.getDefault().post(RefreshEvent(CommendFragment::class.java))
                1 -> EventBus.getDefault().post(RefreshEvent(FollowFragment::class.java))
            }
        } else if (messageEvent is SwitchPagesEvent) {
            when (messageEvent.activityClass) {
                CommendFragment::class.java -> viewPager?.currentItem = 0
                else -> {
                }
            }
        }
    }

    companion object {

        fun newInstance() = CommunityFragment()
    }
}
