package com.example.eyeOpeningKotlin.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.databinding.ActivitySettingBinding
import com.example.eyeOpeningKotlin.ui.common.ui.BaseActivity
import com.example.eyeOpeningKotlin.util.GlobalUtil

/**
 * 设置界面
 *
 * @author driverSkr
 * @since  2023/10/19
 */
class SettingActivity: BaseActivity() {

    private var _binding: ActivitySettingBinding? = null

    private val binding: ActivitySettingBinding
        get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this)[SettingViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun setupViews() {
        super.setupViews()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initTitle()
    }

    private fun initTitle() {
        binding.titleBar.tvTitle.text = GlobalUtil.getString(R.string.settings)
        binding.titleBar.tvRightText.setTextColor(ContextCompat.getColor(this@SettingActivity,R.color.white))
        binding.titleBar.tvRightText.textSize = 12f
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, SettingActivity::class.java))
        }
    }
}