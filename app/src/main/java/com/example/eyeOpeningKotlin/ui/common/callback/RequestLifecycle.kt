package com.example.eyeOpeningKotlin.ui.common.callback

/**
 * 在Activity或Fragment中进行网络请求所需要经历的生命周期函数。
 *
 * @author guolin
 * @since 2023/10/19
 */
interface RequestLifecycle {

    fun startLoading()

    fun loadFinished()

    fun loadFailed(msg: String?)

}