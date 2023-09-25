package com.example.eye_openingKotlin.util

import com.example.eye_openingKotlin.logic.MainPageRepository
import com.example.eye_openingKotlin.logic.VideoRepository
import com.example.eye_openingKotlin.logic.dao.EyepetizerDatabase
import com.example.eye_openingKotlin.logic.network.EyepetizerNetwork
import com.example.eye_openingKotlin.ui.community.follow.FollowViewModelFactory
import com.example.eye_openingKotlin.ui.home.daily.DailyViewModelFactory
import com.example.eye_openingKotlin.ui.home.discovery.DiscoveryViewModelFactory
import com.example.eye_openingKotlin.ui.newdetail.NewDetailViewModelFactory
import com.example.eye_openingKotlin.ui.notification.push.PushViewModelFactory
import com.example.eye_openingKotlin.ui.search.SearchViewModelFactory

/**
 * 应用程序逻辑控制管理类。
 *
 * @author vipyinzhiwei
 * @since  2020/5/2
 */
object InjectorUtil {

    private fun getMainPageRepository() = MainPageRepository.getInstance(EyepetizerDatabase.getMainPageDao(), EyepetizerNetwork.getInstance())

    private fun getViedoRepository() = VideoRepository.getInstance(EyepetizerDatabase.getVideoDao(), EyepetizerNetwork.getInstance())

    fun getDiscoveryViewModelFactory() = DiscoveryViewModelFactory(getMainPageRepository())

    fun getHomePageCommendViewModelFactory() = com.example.eye_openingKotlin.ui.home.commend.CommendViewModelFactory(getMainPageRepository())

    fun getDailyViewModelFactory() = DailyViewModelFactory(getMainPageRepository())

    fun getCommunityCommendViewModelFactory() = com.example.eye_openingKotlin.ui.community.commend.CommendViewModelFactory(getMainPageRepository())

    fun getFollowViewModelFactory() = FollowViewModelFactory(getMainPageRepository())

    fun getPushViewModelFactory() = PushViewModelFactory(getMainPageRepository())

    fun getSearchViewModelFactory() = SearchViewModelFactory(getMainPageRepository())

    fun getNewDetailViewModelFactory() = NewDetailViewModelFactory(getViedoRepository())

}