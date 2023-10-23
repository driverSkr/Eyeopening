package com.example.eyeOpeningKotlin.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.eyeOpeningKotlin.Const
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.databinding.ActivityLoginBinding
import com.example.eyeOpeningKotlin.extension.*
import com.example.eyeOpeningKotlin.ui.common.ui.BaseActivity
import com.example.eyeOpeningKotlin.ui.common.ui.WebViewActivity
import com.example.eyeOpeningKotlin.util.GlobalUtil

/**
 * 登录界面。
 *
 * @author driverSkr
 * @since  2023/10/19
 */
class LoginActivity: BaseActivity() {

    private var _binding: ActivityLoginBinding? = null

    private val binding: ActivityLoginBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarBackground(R.color.black)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun setupViews() {
        super.setupViews()
        initTitleBar()
        initListener()
    }

    private fun initTitleBar() {
        binding.titleBar.titleBar.layoutParams.height = resources.getDimensionPixelSize(R.dimen.actionBarSizeSecondary)
        binding.titleBar.titleBar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        val padding = dp2px(9f)
        binding.titleBar.ivNavigateBefore.setPadding(padding, padding, padding, padding)
        binding.titleBar.ivNavigateBefore.setImageResource(R.drawable.ic_close_white_24dp)
        binding.titleBar.tvRightText.visible()
        binding.titleBar.tvRightText.text = GlobalUtil.getString(R.string.forgot_password)
        binding.titleBar.tvRightText.setTextColor(ContextCompat.getColor(this@LoginActivity, R.color.white))
        binding.titleBar.tvRightText.textSize = 12f
        binding.titleBar.divider.gone()
        binding.etPhoneNumberOrEmail.setDrawable(ContextCompat.getDrawable(this, R.drawable.ic_person_white_18dp), 18f, 18f, 0)
        binding.etPassWord.setDrawable(ContextCompat.getDrawable(this, R.drawable.ic_password_white_lock_18dp), 18f, 18f, 0)
    }

    private fun initListener() {
        setOnClickListener(
            binding.titleBar.tvRightText, binding.tvUserLogin, binding.tvUserRegister, binding.tvAuthorLogin, binding.tvUserAgreement, binding.tvUserLogin,
            binding.ivWechat, binding.ivSina, binding.ivQQ
        ) {
            when (this) {
                binding.titleBar.tvRightText -> {
                    WebViewActivity.start(this@LoginActivity, WebViewActivity.DEFAULT_TITLE, Const.Url.FORGET_PASSWORD, false, false)
                }
                binding.tvUserRegister -> {
                    WebViewActivity.start(this@LoginActivity, WebViewActivity.DEFAULT_TITLE, Const.Url.AUTHOR_REGISTER, false, false)
                }
                binding.tvAuthorLogin -> {
                    WebViewActivity.start(this@LoginActivity, WebViewActivity.DEFAULT_TITLE, Const.Url.AUTHOR_LOGIN, false, false)
                }
                binding.tvUserAgreement -> {
                    WebViewActivity.start(this@LoginActivity, WebViewActivity.DEFAULT_TITLE, Const.Url.USER_AGREEMENT, false, false)
                }
                binding.tvUserLogin, binding.ivWechat, binding.ivSina, binding.ivQQ -> {
                    R.string.currently_not_supported.showToast()
                }
            }
        }
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}