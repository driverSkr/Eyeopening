package com.example.eye_openingJava.extension;

import android.widget.Toast;

import com.example.eye_openingJava.EyeopeningApplication;
import com.tencent.sonic.sdk.SonicConfig;
import com.tencent.sonic.sdk.SonicEngine;
import com.tencent.sonic.sdk.SonicSessionConfig;

/**
 *@Author: create by boge
 *@Createtime: 2023/9/7 15:14
 *@Description: 对CharSequence进行功能扩展
 * list=> showToast()
*         preCreateSession()
*/
public class CharSequenceExt {
    /**
     * 弹出Toast提示。
     * @param message  要显示的消息
     * @param duration 显示消息的时间，可以是 Toast.LENGTH_SHORT 或 Toast.LENGTH_LONG
     */
    public static void showToast(String message, int duration) {
        Toast.makeText(EyeopeningApplication.context, message, duration).show();
    }

    /**
     * VasSonic预加载session。
     *
     * @param url 要预加载的URL
     * @return 预加载是否成功
     */
    public static boolean preCreateSession(CharSequence url) {
        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(new SonicRuntimeImpl(EyeopeningApplication.context), new SonicConfig.Builder().build());
        }
        SonicSessionConfig.Builder sessionConfigBuilder = new SonicSessionConfig.Builder();
        sessionConfigBuilder.setSupportLocalServer(true);
        return SonicEngine.getInstance().preCreateSession(url.toString(), sessionConfigBuilder.build());
    }
}
