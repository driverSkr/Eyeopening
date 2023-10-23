package com.example.eyeOpeningKotlin.ui.common.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.event.MessageEvent
import com.example.eyeOpeningKotlin.extension.logD
import com.example.eyeOpeningKotlin.util.ActivityCollector
import com.example.eyeOpeningKotlin.util.ShareUtil
import com.gyf.immersionbar.ImmersionBar
import com.umeng.analytics.MobclickAgent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.ref.WeakReference

/**
 * 应用程序中所有Activity的基类。
 *
 * @author driverSkr
 * @since  2023/10/18
 */
open class BaseActivity: AppCompatActivity() {

    /**
     * 判断当前Activity是否在前台。
     */
    protected var isActive: Boolean = false

    /**
     * 当前Activity的实例。
     */
    protected var activity: Activity? = null

    /** 当前Activity的弱引用，防止内存泄露  */
    private var activityWR: WeakReference<Activity>? = null

    /**
     * 日志输出标志
     */
    protected var TAG: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        logD(TAG,"BaseActivity-->onCreate()")

        activity = this
        activityWR = WeakReference(activity!!)
        ActivityCollector.pushTask(activityWR)

        EventBus.getDefault().register(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logD(TAG, "BaseActivity-->onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        logD(TAG, "BaseActivity-->onRestoreInstanceState()")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logD(TAG, "BaseActivity-->onNewIntent()")
    }

    override fun onRestart() {
        super.onRestart()
        logD(TAG, "BaseActivity-->onRestart()")
    }

    override fun onStart() {
        super.onStart()
        logD(TAG, "BaseActivity-->onStart()")
    }

    override fun onResume() {
        super.onResume()
        logD(TAG,"BaseActivity-->onResume()")
        isActive = true
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        logD(TAG, "BaseActivity-->onPause()")
        isActive = false
        MobclickAgent.onPause(this)
    }

    override fun onStop() {
        super.onStop()
        logD(TAG, "BaseActivity-->onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logD(TAG, "BaseActivity-->onDestroy()")

        activity = null
        ActivityCollector.removeTask(activityWR)
        EventBus.getDefault().unregister(this)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        //设置状态栏背景
        setStatusBarBackground(R.color.colorPrimaryDark)
        setupViews()
    }

    override fun setContentView(layoutView: View) {
        super.setContentView(layoutView)
        //设置状态栏背景
        setStatusBarBackground(R.color.colorPrimaryDark)
        setupViews()
    }

    //用于设置视图（Views）的初始化和配置
    protected open fun setupViews(){
        //标题旁的返回按钮
        val navigateBefore = findViewById<ImageView>(R.id.ivNavigateBefore)
        //页面的标题
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        //返回或导航前一步
        navigateBefore?.setOnClickListener{ finish() }
        //允许文本在 TextView 控件中水平滚动，以便显示长文本
        tvTitle?.isSelected = true  //获取焦点，实现跑马灯效果。
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(messageEvent: MessageEvent) {

    }

    /**
     * 设置状态栏背景色
     */
    open fun setStatusBarBackground(@ColorRes statusBarColor: Int) {
        ImmersionBar.with(this)
            .autoStatusBarDarkModeEnable(true, 0.2f)//自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
            .statusBarColor(statusBarColor)//状态栏颜色，不写默认透明色
            .fitsSystemWindows(true)//解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色，还有一些重载方法
            .init()
    }

    /**
     * 调用系统原生分享
     *
     * @param shareContent 分享内容
     * @param shareType SHARE_MORE=0，SHARE_QQ=1，SHARE_WECHAT=2，SHARE_WEIBO=3，SHARE_QQZONE=4
     */
    protected fun share(shareContent: String,shareType: Int){
        ShareUtil.share(this,shareContent,shareType)
    }

    /**
     * 弹出分享对话框
     *
     * @param shareContent 分享内容
     */
    protected fun showDialogShare(shareContent: String){
        com.example.eyeOpeningKotlin.extension.showDialogShare(this,shareContent)
    }
}