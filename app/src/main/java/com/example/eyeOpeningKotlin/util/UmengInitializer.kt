package com.example.eyeOpeningKotlin.util

import android.content.Context
import androidx.startup.Initializer
import com.example.eyeOpeningKotlin.BuildConfig
import com.umeng.commonsdk.UMConfigure

/**
 * App Startup
 *
 * @author driverSkr
 * @since  2023/10/25
 */
class UmengInitializer: Initializer<Unit> {

    override fun create(context: Context) {
        UMConfigure.init(context, UMConfigure.DEVICE_TYPE_PHONE, null)
        UMConfigure.setLogEnabled(BuildConfig.DEBUG)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}