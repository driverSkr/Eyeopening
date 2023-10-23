package com.example.eyeOpeningKotlin.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import com.example.eyeOpeningKotlin.Const
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.databinding.ActivityAboutBinding
import com.example.eyeOpeningKotlin.ui.common.ui.BaseActivity
import com.example.eyeOpeningKotlin.ui.common.ui.WebViewActivity
import com.example.eyeOpeningKotlin.ui.common.ui.WebViewActivity.Companion.DEFAULT_TITLE
import com.example.eyeOpeningKotlin.ui.common.ui.WebViewActivity.Companion.DEFAULT_URL
import com.example.eyeOpeningKotlin.util.GlobalUtil
import com.umeng.analytics.MobclickAgent

/**
 * 关于界面。
 *
 * @author driverSkr
 * @since  2023/10/19
 */
class AboutActivity: BaseActivity() {

    private var _binding: ActivityAboutBinding? = null

    private val binding: ActivityAboutBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun setupViews() {
        super.setupViews()
        binding.titleBar.tvTitle.text = GlobalUtil.getString(R.string.about)
        val version = "${GlobalUtil.getString(R.string.version)} ${GlobalUtil.appVersionName}"
        binding.tvAboutVersion.text = version
        binding.tvThanksTips.text = String.format(GlobalUtil.getString(R.string.thanks_to), GlobalUtil.appName)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.tvOpenSourceList.text = Html.fromHtml("<u>" + GlobalUtil.getString(R.string.open_source_project_list) + "</u>", 0)
        } else {
            binding.tvOpenSourceList.text = Html.fromHtml("<u>" + GlobalUtil.getString(R.string.open_source_project_list) + "</u>")
        }
        binding.ivLogo.setImageDrawable(GlobalUtil.getAppIcon())

        binding.btnEncourage.setOnClickListener {
            MobclickAgent.onEvent(activity, Const.Mobclick.EVENT5)
            WebViewActivity.start(this, DEFAULT_TITLE, DEFAULT_URL, true)
        }
        binding.tvOpenSourceList.setOnClickListener {
            OpenSourceProjectsActivity.start(this@AboutActivity)
        }
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, AboutActivity::class.java))
        }
    }
}