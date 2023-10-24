package com.example.eyeOpeningKotlin.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.event.MessageEvent
import com.example.eyeOpeningKotlin.event.RefreshEvent
import com.example.eyeOpeningKotlin.event.SwitchPagesEvent
import com.example.eyeOpeningKotlin.logic.model.TabEntity
import com.example.eyeOpeningKotlin.ui.common.ui.BaseViewPagerFragment
import com.example.eyeOpeningKotlin.ui.community.commend.CommendFragment
import com.example.eyeOpeningKotlin.ui.community.follow.FollowFragment
import com.example.eyeOpeningKotlin.util.GlobalUtil
import com.flyco.tablayout.listener.CustomTabEntity
import org.greenrobot.eventbus.EventBus

/**
 * 社区主界面。
 *
 * @author driverSkr
 * @since  2023/10/19
 */
class CommunityFragment: BaseViewPagerFragment() {

    /**定义选项卡的标题*/
    override val createTitles = ArrayList<CustomTabEntity>().apply {
        add(TabEntity(GlobalUtil.getString(R.string.commend)))
        add(TabEntity(GlobalUtil.getString(R.string.follow)))
    }

    /**创建了一个包含两个 Fragment 对象的数组*/
    override val createFragments: Array<Fragment> = arrayOf(CommendFragment.newInstance(), FollowFragment.newInstance())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater.inflate(R.layout.fragment_main_container,container,false))
    }

    /**
     * 处理不同类型的事件
     */
    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)//在处理事件之前会先执行父类的事件处理逻辑
        /**刷新事件*/
        if (messageEvent is RefreshEvent && this::class.java == messageEvent.activityClass) {
            when (viewPager?.currentItem) {
                0 -> EventBus.getDefault().post(RefreshEvent(CommendFragment::class.java))
                1 -> EventBus.getDefault().post(RefreshEvent(FollowFragment::class.java))
            }
        }
        /**页面切换事件*/
        else if (messageEvent is SwitchPagesEvent) {
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