package com.example.eyeOpeningKotlin.ui.notification

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
import com.example.eyeOpeningKotlin.ui.notification.inbox.InboxFragment
import com.example.eyeOpeningKotlin.ui.notification.interaction.InteractionFragment
import com.example.eyeOpeningKotlin.ui.notification.push.PushFragment
import com.example.eyeOpeningKotlin.util.GlobalUtil
import com.flyco.tablayout.listener.CustomTabEntity
import org.greenrobot.eventbus.EventBus

/**
 * 通知主界面。
 *
 * @author boge
 * @since  2023/10/19
 */
class NotificationFragment: BaseViewPagerFragment() {

    override val createTitles = ArrayList<CustomTabEntity>().apply {
        add(TabEntity(GlobalUtil.getString(R.string.push)))
        add(TabEntity(GlobalUtil.getString(R.string.interaction)))
        add(TabEntity(GlobalUtil.getString(R.string.inbox)))
    }

    override val createFragments: Array<Fragment> = arrayOf(PushFragment.newInstance(), InteractionFragment.newInstance(), InboxFragment.newInstance())


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater.inflate(R.layout.fragment_main_container, container, false))
    }

    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)
        if (messageEvent is RefreshEvent && this::class.java == messageEvent.activityClass) {
            when (viewPager?.currentItem) {
                0 -> EventBus.getDefault().post(RefreshEvent(PushFragment::class.java))
                1 -> EventBus.getDefault().post(RefreshEvent(InteractionFragment::class.java))
                2 -> EventBus.getDefault().post(RefreshEvent(InboxFragment::class.java))
            }
        } else if (messageEvent is SwitchPagesEvent) {
            when (messageEvent.activityClass) {
                PushFragment::class.java -> viewPager?.currentItem = 0
                InteractionFragment::class.java -> viewPager?.currentItem = 1
                InboxFragment::class.java -> viewPager?.currentItem = 2
            }
        }
    }

    companion object {

        fun newInstance() = NotificationFragment()
    }
}