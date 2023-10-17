package com.example.eyeOpeningKotlin.extension

import com.example.eyeOpeningKotlin.EyeopeningApplication

/**
 * 根据手机的分辨率将dp转成为px。
 */
fun dp2px(dp: Float): Int{
    val scale = EyeopeningApplication.context.resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

/**
 * 根据手机的分辨率将px转成dp。
 */
fun px2dp(px: Float): Int{
    val scale = EyeopeningApplication.context.resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}

/**
 * 获取屏幕宽值。
 */
//当你每次访问screenWidth属性时，get()方法会执行一次，以获取最新的数据。
// 这意味着每次你使用screenWidth，它会重新计算并返回当前的屏幕高度像素数
val screenWidth get() = EyeopeningApplication.context.resources.displayMetrics.heightPixels

/**
 * 获取屏幕高值。
 */
val screenHeight get() = EyeopeningApplication.context.resources.displayMetrics.heightPixels

/**
 * 获取屏幕像素：对获取的宽高进行拼接。例：1080X2340。
 */
//run():只接收一个Lambda参数，并且会在Lambda表达式中提供调用对象的上下文
//Lambda表达式中的最后一行代码作为返回值返回
fun screenPixel(): String{
    EyeopeningApplication.context.resources.displayMetrics.run {
        return "${widthPixels}X${heightPixels}"
    }
}