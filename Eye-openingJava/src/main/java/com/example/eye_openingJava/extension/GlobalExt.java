package com.example.eye_openingJava.extension;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eye_openingJava.EyeopeningApplication;
import com.example.eye_openingJava.ui.common.ui.ShareDialogFragment;
import com.example.eye_openingJava.util.GlobalUtil;
import com.example.eye_openingJava.util.ShareUtil;

public class GlobalExt {

    /**
     * 获取DataStore实例。
     *//*
    private static final RxDataStore<Preferences> dataStore = new RxPreferenceDataStoreBuilder(EyeopeningApplication.context, "dataStoreName").build();

    private final DataStore<Preferences> dataStore = EyeopeningApplication.context.getDataStore();*/

    /**
     * 获取SharedPreferences实例。
     */
    private static final SharedPreferences sharedPreferences = EyeopeningApplication.context.getSharedPreferences(GlobalUtil.getAppName(EyeopeningApplication.context) + "_preferences", Context.MODE_PRIVATE);

    /**
     * 批量设置控件点击事件。
     *
     * @param v       点击的控件
     * @param listener   处理点击事件回调代码块
     */
    public static void setOnClickListener(View.OnClickListener listener, View... v) {
        for (View view : v) {
            if (view != null) {
                view.setOnClickListener(listener);
            }
        }
    }

    /**
     * 调用系统原生分享。
     *
     * @param activity     上下文
     * @param shareContent 分享内容
     * @param shareType    SHARE_MORE=0，SHARE_QQ=1，SHARE_WECHAT=2，SHARE_WEIBO=3，SHARE_QQZONE=4
     */
    public static void share(Activity activity, String shareContent, int shareType) {
        ShareUtil.share(activity, shareContent, shareType);
    }

    /**
     * 弹出分享对话框。
     *
     * @param activity     上下文
     * @param shareContent 分享内容
     */
    public static void showDialogShare(Activity activity, String shareContent) {
        if (activity instanceof AppCompatActivity) {
            ShareDialogFragment.showDialog((AppCompatActivity) activity, shareContent);
        }
    }

    /**
     * 获取DataStore实例。
     *//*
    public static SingleProcessDataStore<Preferences> getDataStore() {
        return dataStore;
    }*/

    /**
     * 获取SharedPreferences实例。
     */
    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
