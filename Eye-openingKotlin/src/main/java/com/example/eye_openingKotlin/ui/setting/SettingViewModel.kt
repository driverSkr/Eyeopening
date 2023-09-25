package com.example.eye_openingKotlin.ui.setting

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.eye_openingKotlin.Const
import com.example.eye_openingKotlin.R
import com.example.eye_openingKotlin.extension.showToast
import com.example.eye_openingKotlin.ui.common.ui.WebViewActivity
import com.example.eye_openingKotlin.ui.common.ui.WebViewActivity.Companion.MODE_SONIC_WITH_OFFLINE_CACHE
import com.example.eye_openingKotlin.ui.login.LoginActivity
import com.example.eye_openingKotlin.util.DataStoreUtils
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.tencent.sonic.sdk.SonicEngine
import com.umeng.analytics.MobclickAgent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingViewModel : ViewModel() {

    var rbDailyOpen: Boolean
        get() = DataStoreUtils.readBooleanData("dailyOnOff", true)
        set(value) = DataStoreUtils.saveSyncBooleanData("dailyOnOff", value)


    var rbWiFiOpen: Boolean
        get() = DataStoreUtils.readBooleanData("wifiOnOff", true)
        set(value) = DataStoreUtils.saveSyncBooleanData("wifiOnOff", value)

    var rbTranslateOpen: Boolean
        get() = DataStoreUtils.readBooleanData("translateOnOff", true)
        set(value) = DataStoreUtils.saveSyncBooleanData("translateOnOff", value)

    fun onClick(view: View) {
        when (view.id) {
            R.id.tvClearCache -> {
                clearAllCache(view.context)
            }
            R.id.tvOptionCachePath, R.id.tvOptionPlayDefinition, R.id.tvOptionCacheDefinition -> {
                LoginActivity.start(view.context)
            }
            R.id.tvCheckVersion -> {
                R.string.currently_not_supported.showToast()
            }
            R.id.tvUserAgreement -> {
                WebViewActivity.start(view.context, WebViewActivity.DEFAULT_TITLE, Const.Url.USER_AGREEMENT, false, false)
            }
            R.id.tvLegalNotices -> {
                WebViewActivity.start(view.context, WebViewActivity.DEFAULT_TITLE, Const.Url.LEGAL_NOTICES, false, false)
            }
            R.id.tvVideoFunStatement, R.id.tvCopyrightReport -> {
                WebViewActivity.start(view.context, WebViewActivity.DEFAULT_TITLE, Const.Url.VIDEO_FUNCTION_STATEMENT, false, false)
            }
            R.id.tvSlogan, R.id.tvDescription -> {
                WebViewActivity.start(view.context, WebViewActivity.DEFAULT_TITLE, WebViewActivity.DEFAULT_URL, true, false, MODE_SONIC_WITH_OFFLINE_CACHE)
            }
            R.id.llScrollViewContent -> {
                MobclickAgent.onEvent(view.context, Const.Mobclick.EVENT6)
                AboutActivity.start(view.context)
            }
        }
    }

    private fun clearAllCache(context: Context) {
        viewModelScope.launch(Dispatchers.Main) {
            GSYVideoManager.instance().clearAllDefaultCache(context)
            Glide.get(context).clearMemory()
            withContext(Dispatchers.IO) {
                Glide.get(context).clearDiskCache()
                if (SonicEngine.isGetInstanceAllowed()) {
                    SonicEngine.getInstance().cleanCache()
                }
            }
            R.string.clear_cache_succeed.showToast()
        }
    }
}