package com.example.eyeOpeningKotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.databinding.FragmentMainContainerBinding
import com.example.eyeOpeningKotlin.event.MessageEvent
import com.example.eyeOpeningKotlin.event.RefreshEvent
import com.example.eyeOpeningKotlin.event.SwitchPagesEvent
import com.example.eyeOpeningKotlin.logic.model.TabEntity
import com.example.eyeOpeningKotlin.ui.common.ui.BaseViewPagerFragment
import com.example.eyeOpeningKotlin.ui.home.commend.CommendFragment
import com.example.eyeOpeningKotlin.ui.home.discovery.DiscoveryFragment
import com.example.eyeOpeningKotlin.util.GlobalUtil
import com.flyco.tablayout.listener.CustomTabEntity
import org.greenrobot.eventbus.EventBus

/**
 * 首页主界面。
 *
 * @author boge
 * @since  2023/10/19
 */
class HomePageFragment: BaseViewPagerFragment() {

    /*private var _binding: FragmentMainContainerBinding? = null

    private val binding
        get() = _binding!!

    override val createTitles = ArrayList<CustomTabEntity>().apply {
        add(TabEntity(GlobalUtil.getString(R.string.discovery)))
        add(TabEntity(GlobalUtil.getString(R.string.commend)))
        add(TabEntity(GlobalUtil.getString(R.string.daily)))
    }

    override val createFragments: Array<Fragment> = arrayOf(DiscoveryFragment.newInstance(), CommendFragment.newInstance(), DailyFragment.newInstance())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainContainerBinding.inflate(layoutInflater, container, false)
        return super.onCreateView(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleBar.ivCalendar.visibility = View.VISIBLE
        viewPager?.currentItem = 1
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMessageEvent(messageEvent: MessageEvent) {
        super.onMessageEvent(messageEvent)
        if (messageEvent is RefreshEvent && this::class.java == messageEvent.activityClass) {
            when (viewPager?.currentItem) {
                0 -> EventBus.getDefault().post(RefreshEvent(DiscoveryFragment::class.java))
                1 -> EventBus.getDefault().post(RefreshEvent(CommendFragment::class.java))
                2 -> EventBus.getDefault().post(RefreshEvent(DailyFragment::class.java))
            }
        } else if (messageEvent is SwitchPagesEvent) {
            when (messageEvent.activityClass) {
                DiscoveryFragment::class.java -> viewPager?.currentItem = 0
                CommendFragment::class.java -> viewPager?.currentItem = 1
                DailyFragment::class.java -> viewPager?.currentItem = 2
            }
        }
    }

    companion object {

        fun newInstance() = HomePageFragment()
    }*/
}