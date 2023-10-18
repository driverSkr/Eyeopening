package com.example.eyeOpeningKotlin.extension

import android.widget.Toast
import com.example.eyeOpeningKotlin.EyeopeningApplication
import com.example.eyeOpeningKotlin.ui.common.ui.vassonic.SonicRuntimeImpl
import com.tencent.sonic.sdk.SonicConfig
import com.tencent.sonic.sdk.SonicEngine
import com.tencent.sonic.sdk.SonicSessionConfig

/**
 * 弹出Toast提示。
 * @param duration 显示消息的时间  Either {@link #LENGTH_SHORT} or {@link #LENGTH_LONG}
 */
fun CharSequence.showToast(duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(EyeopeningApplication.context,this,duration).show()
}

/**
 * VasSonic预加载session。
 *
 * @param CharSequence 预加载url
 */
fun CharSequence.preCreateSession(): Boolean {
    if (!SonicEngine.isGetInstanceAllowed()) {
        SonicEngine.createInstance(SonicRuntimeImpl(EyeopeningApplication.context), SonicConfig.Builder().build())
    }
    val sessionConfigBuilder = SonicSessionConfig.Builder().apply { setSupportLocalServer(true) }
    val preloadSuccess = SonicEngine.getInstance().preCreateSession(this.toString(), sessionConfigBuilder.build())
    return preloadSuccess
}
