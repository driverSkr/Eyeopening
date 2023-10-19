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
 * VasSonic预加载session(会话)。
 *
 * @param CharSequence 预加载url
 */
fun CharSequence.preCreateSession(): Boolean {
    //用于检查是否已经存在 Sonic 引擎实例
    if (!SonicEngine.isGetInstanceAllowed()) {
        //如果尚未创建 Sonic 引擎实例，它将创建一个新的 Sonic 引擎实例
        //这里使用了 SonicRuntimeImpl 和 SonicConfig 来配置 Sonic 引擎
        SonicEngine.createInstance(SonicRuntimeImpl(EyeopeningApplication.context), SonicConfig.Builder().build())
    }
    //创建一个 Sonic 会话配置生成器，并设置了会话配置
    val sessionConfigBuilder = SonicSessionConfig.Builder().apply { setSupportLocalServer(true) /*表示会话支持本地服务器*/ }
    //使用 Sonic引擎实例的 preCreateSession 方法来预加载会话
    /*
    * this.toString() 获取 CharSequence 对象的字符串表示，以用作预加载的 URL
    * sessionConfigBuilder.build() 用于构建会话配置
    * */
    val preloadSuccess = SonicEngine.getInstance().preCreateSession(this.toString(), sessionConfigBuilder.build())
    //表示预加载是否成功
    return preloadSuccess
}
