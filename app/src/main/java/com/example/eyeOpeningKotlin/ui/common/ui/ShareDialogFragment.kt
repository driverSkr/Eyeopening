package com.example.eyeOpeningKotlin.ui.common.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.eyeOpeningKotlin.Const
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.databinding.FragmentShareDialogBinding
import com.example.eyeOpeningKotlin.extension.setDrawable
import com.example.eyeOpeningKotlin.extension.share
import com.example.eyeOpeningKotlin.util.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.umeng.analytics.MobclickAgent

/**
 * 分享对话框的弹出界面。
 *
 * @author driverSkr
 * @since  2023/10/18
 */
class ShareDialogFragment: BottomSheetDialogFragment() {

    private var _binding: FragmentShareDialogBinding? = null

    private val binding get() = _binding!!

    private lateinit var shareContent: String

    private lateinit var attachedActivity: Activity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShareDialogBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            attachedActivity = it
            binding.tvToWechatFriends.setDrawable(ContextCompat.getDrawable(it, R.drawable.ic_share_wechat_black_30dp),30f,30f,1)
            binding.tvShareToWeibo.setDrawable(ContextCompat.getDrawable(it, R.drawable.ic_share_weibo_black_30dp), 30f, 30f, 1)
            binding.tvShareToQQ.setDrawable(ContextCompat.getDrawable(it, R.drawable.ic_share_qq_black_30dp), 30f, 30f, 1)
            binding.tvShareToQQzone.setDrawable(ContextCompat.getDrawable(it, R.drawable.ic_share_qq_zone_black_30dp), 30f, 30f, 1)

            binding.tvShareToQQ.setOnClickListener {
                share(attachedActivity,shareContent, SHARE_QQ)
                dismiss()
            }
            binding.tvToWechatFriends.setOnClickListener {
                share(attachedActivity,shareContent, SHARE_WECHAT)
                dismiss()
            }
            binding.tvShareToWeibo.setOnClickListener {
                share(attachedActivity, shareContent, SHARE_WEIBO)
                dismiss()
            }
            binding.tvShareToQQzone.setOnClickListener {
                share(attachedActivity, shareContent, SHARE_QQZONE)
                dismiss()
            }
            binding.llMore.setOnClickListener {
                share(attachedActivity, shareContent, SHARE_MORE)
                dismiss()
            }
            binding.tvCancel.setOnClickListener {
                dismiss()
            }
        }
    }

    fun showDialog(activity: AppCompatActivity,shareContent: String){
        if (shareContent.contains(WebViewActivity.DEFAULT_URL)) {
            MobclickAgent.onEvent(activity, Const.Mobclick.EVENT1)
        } else {
            MobclickAgent.onEvent(activity, Const.Mobclick.EVENT2)
        }
        show(activity.supportFragmentManager, "share_dialog")
        this.shareContent = shareContent
    }
}