package com.example.eye_openingJava.ui.common.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eye_openingJava.R;
import com.example.eye_openingJava.event.MessageEvent;
import com.example.eye_openingJava.util.ActivityCollector;
import com.example.eye_openingJava.util.ShareUtil;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

/**
 *@Author: create by boge
 *@Createtime: 2023/9/6 16:51
 *@Function:
 * 应用程序中所有Activity的基类。
*/
public class BaseActivity extends AppCompatActivity {
    /**
     * 判断当前 Activity 是否在前台。
     */
    protected boolean isActive = false;

    /**
     * 当前 Activity 的实例。
     */
    protected Activity activity = null;

    /** 当前 Activity 的弱引用，防止内存泄露  */
    private WeakReference<Activity> activityWR = null;

    /**
     * 日志输出标志
     */
    protected String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "BaseActivity-->onCreate()");

        activity = this;
        activityWR = new WeakReference<>(activity);
        ActivityCollector.pushTask(activityWR);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "BaseActivity-->onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "BaseActivity-->onRestoreInstanceState()");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "BaseActivity-->onNewIntent()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "BaseActivity-->onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "BaseActivity-->onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "BaseActivity-->onResume()");
        isActive = true;
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "BaseActivity-->onPause()");
        isActive = false;
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "BaseActivity-->onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "BaseActivity-->onDestroy()");

        activity = null;
        ActivityCollector.removeTask(activityWR);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBarBackground(R.color.colorPrimaryDark);
        setupViews();
    }

    @Override
    public void setContentView(View layoutView) {
        super.setContentView(layoutView);
        setStatusBarBackground(R.color.colorPrimaryDark);
        setupViews();
    }

    protected void setupViews() {
        ImageView navigateBefore = findViewById(R.id.ivNavigateBefore);
        TextView tvTitle = findViewById(R.id.tvTitle);
        if (navigateBefore != null) {
            navigateBefore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        if (tvTitle != null) {
            tvTitle.setSelected(true);  // 获取焦点，实现跑马灯效果。
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        // 事件处理代码
    }

    /**
     * 设置状态栏背景色
     */
    /*protected void setStatusBarBackground(@ColorRes int statusBarColor) {
        ImmersionBar.with(this).autoStatusBarDarkModeEnable(true, 0.2f).statusBarColor(statusBarColor).fitsSystemWindows(true).init();
    }*/

    /**
     * 调用系统原生分享
     *
     * @param shareContent 分享内容
     * @param shareType SHARE_MORE=0，SHARE_QQ=1，SHARE_WECHAT=2，SHARE_WEIBO=3，SHARE_QQZONE=4
     */
    protected void share(String shareContent, int shareType) {
        ShareUtil.share(this, shareContent, shareType);
    }

    /**
     * 弹出分享对话框
     *
     * @param shareContent 分享内容
     */
    protected void showDialogShare(String shareContent) {
        com.example.eye_openingJava.extension.showDialogShare(this, shareContent);
    }
}
