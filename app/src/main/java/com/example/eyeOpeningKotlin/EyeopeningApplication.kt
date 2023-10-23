package com.example.eyeOpeningKotlin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.work.WorkManager
import com.example.eyeOpeningKotlin.extension.preCreateSession
import com.example.eyeOpeningKotlin.ui.SplashActivity
import com.example.eyeOpeningKotlin.ui.common.ui.WebViewActivity
import com.example.eyeOpeningKotlin.ui.common.view.NoStatusFooter
import com.example.eyeOpeningKotlin.util.DialogAppraiseTipsWorker
import com.example.eyeOpeningKotlin.util.GlobalUtil
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.shuyu.gsyvideoplayer.BuildConfig
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager
import tv.danmaku.ijk.media.player.IjkMediaPlayer

/**
 * 自定义Application，在这里进行全局的初始化操作
 *
 * @author driverSkr
 * @since  2023/10/17
 */
class EyeopeningApplication: Application() {

    init {
        SmartRefreshLayout.setDefaultRefreshInitializer{ _, layout ->
            layout.setEnableLoadMore(true)
            layout.setEnableLoadMoreWhenContentNotFull(true)
        }

        SmartRefreshLayout.setDefaultRefreshHeaderCreator{ context, layout ->
            layout.setEnableHeaderTranslationContent(true)
            MaterialHeader(context).setColorSchemeResources(R.color.blue, R.color.blue, R.color.blue)
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator{ context, layout ->
            layout.setEnableFooterFollowWhenNoMoreData(true)
            layout.setEnableFooterTranslationContent(true)
            layout.setFooterHeight(153f)
            layout.setFooterTriggerRate(0.6f)
            NoStatusFooter.REFRESH_FOOTER_NOTHING = GlobalUtil.getString(R.string.footer_not_more)
            NoStatusFooter(context).apply {
                setAccentColorId(R.color.colorTextPrimary)
                setTextTitleSize(16f)
            }
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        IjkPlayerManager.setLogLevel(if (BuildConfig.DEBUG) IjkMediaPlayer.IJK_LOG_WARN else IjkMediaPlayer.IJK_LOG_SILENT)
        WebViewActivity.DEFAULT_URL.preCreateSession()
        if (!SplashActivity.isFirstEntryApp && DialogAppraiseTipsWorker.isNeedShowDialog){
            WorkManager.getInstance(this).enqueue(DialogAppraiseTipsWorker.showDialogWorkRequest)
        }
    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val name = "driverSkr"
    }
}