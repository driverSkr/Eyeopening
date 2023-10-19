package com.example.eyeOpeningKotlin.ui.setting

import android.content.Context
import android.content.Intent
import com.example.eyeOpeningKotlin.ui.common.ui.BaseActivity

/**
 * 设置界面
 *
 * @author boge
 * @since  2023/10/19
 */
class SettingActivity: BaseActivity() {

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, SettingActivity::class.java))
        }
    }
}