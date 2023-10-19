package com.example.eyeOpeningKotlin.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eyeOpeningKotlin.Const
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.databinding.FragmentMineBinding
import com.example.eyeOpeningKotlin.extension.setOnClickListener
import com.example.eyeOpeningKotlin.extension.showToast
import com.example.eyeOpeningKotlin.ui.common.ui.BaseFragment
import com.example.eyeOpeningKotlin.ui.common.ui.WebViewActivity
import com.example.eyeOpeningKotlin.ui.common.ui.WebViewActivity.Companion.MODE_SONIC_WITH_OFFLINE_CACHE
import com.example.eyeOpeningKotlin.ui.login.LoginActivity
import com.example.eyeOpeningKotlin.ui.setting.AboutActivity
import com.example.eyeOpeningKotlin.ui.setting.SettingActivity
import com.example.eyeOpeningKotlin.util.GlobalUtil
import com.shuyu.gsyvideoplayer.BuildConfig
import com.umeng.analytics.MobclickAgent

/**
 * 我的界面。
 *
 * @author boge
 * @since  2020/4/29
 */
class MineFragment: BaseFragment() {

    private var _binding: FragmentMineBinding? = null

    private val binding: FragmentMineBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMineBinding.inflate(inflater, container, false)
        return super.onCreateView(binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvVersionNumber.text = String.format(GlobalUtil.getString(R.string.version_show), GlobalUtil.eyeopeningVersionName)
        setOnClickListener(
            binding.ivMore, binding.ivAvatar, binding.tvLoginTips, binding.tvFavorites, binding.tvCache, binding.tvFollow, binding.tvWatchRecord,
            binding.tvNotificationToggle, binding.tvMyBadge, binding.tvFeedback, binding.tvContribute, binding.tvVersionNumber, rootView, binding.llScrollViewContent
        ) {
            when (this) {
                binding.ivMore -> SettingActivity.start(activity)

                binding.ivAvatar, binding.tvLoginTips, binding.tvFavorites, binding.tvCache, binding.tvFollow, binding.tvWatchRecord, binding.tvNotificationToggle, binding.tvMyBadge -> {
                    LoginActivity.start(activity)
                }
                binding.tvContribute -> {
                    WebViewActivity.start(activity, WebViewActivity.DEFAULT_TITLE, Const.Url.AUTHOR_OPEN, false, false)
                }
                binding.tvFeedback -> {
                    WebViewActivity.start(activity, WebViewActivity.DEFAULT_TITLE, WebViewActivity.DEFAULT_URL, true, false, MODE_SONIC_WITH_OFFLINE_CACHE)
                }
                binding.tvVersionNumber -> {
                    WebViewActivity.start(activity, WebViewActivity.DEFAULT_TITLE, WebViewActivity.DEFAULT_URL, true, false, MODE_SONIC_WITH_OFFLINE_CACHE)
                }
                this@MineFragment.rootView, binding.llScrollViewContent -> {
                    MobclickAgent.onEvent(activity, Const.Mobclick.EVENT4)
                    AboutActivity.start(activity)
                }
            }
        }

        binding.tvVersionNumber.setOnLongClickListener {
            val channel = String.format(GlobalUtil.getString(R.string.channel), GlobalUtil.getApplicationMetaData("UMENG_CHANNEL"))
            val buildType = String.format(GlobalUtil.getString(R.string.build_type), BuildConfig.BUILD_TYPE)
            val versionName = String.format(GlobalUtil.getString(R.string.version_name), BuildConfig.VERSION_NAME)
            "${channel}\n${buildType}\n${versionName}".showToast()
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {

        fun newInstance() = MineFragment()
    }
}