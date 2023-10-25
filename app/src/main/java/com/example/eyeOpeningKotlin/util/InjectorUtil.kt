package com.example.eyeOpeningKotlin.util

import com.example.eyeOpeningKotlin.logic.MainPageRepository
import com.example.eyeOpeningKotlin.logic.VideoRepository
import com.example.eyeOpeningKotlin.logic.dao.EyeOpeningDatabase
import com.example.eyeOpeningKotlin.logic.network.EyeOpeningNetwork
import com.example.eyeOpeningKotlin.ui.community.follow.FollowViewModelFactory
import com.example.eyeOpeningKotlin.ui.home.daily.DailyViewModelFactory
import com.example.eyeOpeningKotlin.ui.home.discovery.DiscoveryViewModelFactory
import com.example.eyeOpeningKotlin.ui.newdetail.NewDetailViewModelFactory
import com.example.eyeOpeningKotlin.ui.notification.push.PushViewModelFactory
import com.example.eyeOpeningKotlin.ui.search.SearchViewModelFactory

/**
 * 应用程序逻辑控制管理类。
 *
 * @author driverSkr
 * @since  2023/10/20
 */
object InjectorUtil {

    private fun getMainPageRepository() = MainPageRepository.getInstance(EyeOpeningDatabase.getMainPageDao(), EyeOpeningNetwork.getInstance())

    private fun getVideoRepository() = VideoRepository.getInstance(EyeOpeningDatabase.getVideoDao(), EyeOpeningNetwork.getInstance())

    fun getDiscoveryViewModelFactory() = DiscoveryViewModelFactory(getMainPageRepository())

    fun getHomePageCommendViewModelFactory() = com.example.eyeOpeningKotlin.ui.home.commend.CommendViewModelFactory(getMainPageRepository())

    fun getDailyViewModelFactory() = DailyViewModelFactory(getMainPageRepository())

    fun getCommunityCommendViewModelFactory() = com.example.eyeOpeningKotlin.ui.community.commend.CommendViewModelFactory(getMainPageRepository())

    fun getFollowViewModelFactory() = FollowViewModelFactory(getMainPageRepository())

    fun getPushViewModelFactory() = PushViewModelFactory(getMainPageRepository())

    fun getSearchViewModelFactory() = SearchViewModelFactory(getMainPageRepository())

    fun getNewDetailViewModelFactory() = NewDetailViewModelFactory(getVideoRepository())
}