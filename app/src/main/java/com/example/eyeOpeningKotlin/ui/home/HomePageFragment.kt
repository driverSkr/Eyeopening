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

    companion object {

        fun newInstance() = HomePageFragment()
    }
}