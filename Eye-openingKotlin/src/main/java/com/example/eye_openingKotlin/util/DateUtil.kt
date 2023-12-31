package com.example.eye_openingKotlin.util

import com.example.eye_openingKotlin.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * 时间和日期工具类。
 *
 * @author guolin
 * @since 17/6/30
 */
object DateUtil {

    const val MINUTE = (60 * 1000).toLong()

    const val HOUR = 60 * MINUTE

    const val DAY = 24 * HOUR

    const val WEEK = 7 * DAY

    const val MONTH = 4 * WEEK

    const val YEAR = 365 * DAY

    /**
     * 根据传入的Unix时间戳，获取转换过后更加易读的时间格式。
     * @param dateMillis
     * Unix时间戳
     * @return 转换过后的时间格式，如2分钟前，1小时前。
     */
    fun getConvertedDate(dateMillis: Long): String {
        val currentMillis = System.currentTimeMillis()
        val timePast = currentMillis - dateMillis
        if (timePast > -MINUTE) { // 采用误差一分钟以内的算法，防止客户端和服务器时间不同步导致的显示问题
            when {
                /*timePast < HOUR -> {
                    var pastMinutes = timePast / MINUTE
                    if (pastMinutes <= 0) {
                        pastMinutes = 1
                    }
                    return pastMinutes.toString() + GlobalUtil.getString(R.string.minutes_ago)
                }*/
                timePast < DAY -> {
                    /*var pastHours = timePast / HOUR
                    if (pastHours <= 0) {
                        pastHours = 1
                    }
                    return pastHours.toString() + GlobalUtil.getString(R.string.hours_ago)*/
                    return getDateAndHourMinuteTime(dateMillis)
                }
                timePast < WEEK -> {
                    var pastDays = timePast / DAY
                    if (pastDays <= 0) {
                        pastDays = 1
                    }
                    return "$pastDays ${GlobalUtil.getString(R.string.days_ago)}"
                }
                timePast < MONTH -> {
                    var pastDays = timePast / WEEK
                    if (pastDays <= 0) {
                        pastDays = 1
                    }
                    return "$pastDays ${GlobalUtil.getString(R.string.weeks_ago)}"
                }
                else -> return getDate(dateMillis)
            }
        } else {
            return getDateAndTime(dateMillis)
        }
    }

    fun isBlockedForever(timeLeft: Long) = timeLeft > 5 * YEAR

    fun getDate(dateMillis: Long, pattern: String = "yyyy-MM-dd") = SimpleDateFormat(pattern, Locale.getDefault()).format(Date(dateMillis))

    private fun getDateAndTime(dateMillis: Long) = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(dateMillis))

    private fun getDateAndHourMinuteTime(dateMillis: Long) = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(dateMillis))

}
