package com.example.eyeOpeningKotlin.util

import java.lang.ref.WeakReference

/**
 * 数据传输工具类，处理Intent携带大量数据时，超过1MB限制出现的异常场景。
 *
 * @author driverSkr
 * @since  2023/10/23
 */
object IntentDataHolderUtil {

    private val map = hashMapOf<String, WeakReference<Any>>()

    fun setData(key: String, t: Any) {
        val value = WeakReference(t)
        map[key] = value
    }

    fun <T> getData(key: String): T? {
        val reference = map[key]
        return try {
            reference?.get() as T
        } catch (e: Exception) {
            null
        }
    }
}