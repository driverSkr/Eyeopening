package com.example.eye_openingKotlin.ui.common.callback

/**
 * 在Activity或Fragment中进行网络请求所需要经历的生命周期函数。
 *
 * @author guolin
 * @since 18/4/2
 */
interface RequestLifecycle {

    fun startLoading()

    fun loadFinished()

    fun loadFailed(msg: String?)

}
