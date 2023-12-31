package com.example.eye_openingKotlin.util

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.example.eye_openingKotlin.logic.network.ServiceCreator
import java.io.InputStream

/**
 * 自定义Glide模块，用于修改Glide默认的配置。
 *
 * @author vipyinzhiwei
 * @since  2020/5/2
 */
@GlideModule
class EyepetizerGlideModule : AppGlideModule() {

    override fun isManifestParsingEnabled() = false

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(ServiceCreator.httpClient))
    }
}