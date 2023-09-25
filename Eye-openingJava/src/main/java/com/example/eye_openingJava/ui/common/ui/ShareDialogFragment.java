package com.example.eye_openingJava.ui.common.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.eye_openingJava.R;
import com.example.eye_openingJava.databinding.FragmentShareDialogBinding;
import com.example.eye_openingJava.extension.GlobalExt;
import com.example.eye_openingJava.extension.TextViewExt;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.umeng.analytics.MobclickAgent;

public class ShareDialogFragment extends BottomSheetDialogFragment {

    private FragmentShareDialogBinding binding;

    private String shareContent;
    private Activity attachedActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentShareDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Activity activity = getActivity();
        if (activity != null) {
            attachedActivity = activity;
            TextViewExt.setDrawable(binding.tvToWechatFriends,ContextCompat.getDrawable(activity, R.drawable.ic_share_wechat_black_30dp), 30f, 30f, 1);
            TextViewExt.setDrawable(binding.tvShareToWeibo,ContextCompat.getDrawable(activity, R.drawable.ic_share_weibo_black_30dp), 30f, 30f, 1);
            TextViewExt.setDrawable(binding.tvShareToQQ,ContextCompat.getDrawable(activity, R.drawable.ic_share_qq_black_30dp), 30f, 30f, 1);
            TextViewExt.setDrawable(binding.tvShareToQQzone,ContextCompat.getDrawable(activity, R.drawable.ic_share_qq_zone_black_30dp), 30f, 30f, 1);

            binding.tvShareToQQ.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GlobalExt.share(attachedActivity, shareContent, SHARE_QQ);
                    dismiss();
                }
            });
            binding.tvToWechatFriends.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GlobalExt.share(attachedActivity, shareContent, SHARE_WECHAT);
                    dismiss();
                }
            });
            binding.tvShareToWeibo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GlobalExt.share(attachedActivity, shareContent, SHARE_WEIBO);
                    dismiss();
                }
            });
            binding.tvShareToQQzone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GlobalExt.share(attachedActivity, shareContent, SHARE_QQZONE);
                    dismiss();
                }
            });
            binding.llMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GlobalExt.share(attachedActivity, shareContent, SHARE_MORE);
                    dismiss();
                }
            });
            binding.tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }

    public void showDialog(AppCompatActivity activity, String shareContent) {
        if (shareContent.contains(WebViewActivity.DEFAULT_URL)) {
            MobclickAgent.onEvent(activity, Const.Mobclick.EVENT1);
        } else {
            MobclickAgent.onEvent(activity, Const.Mobclick.EVENT2);
        }
        show(activity.getSupportFragmentManager(), "share_dialog");
        this.shareContent = shareContent;
    }

    // 请根据您的需求定义这些常量
    private static final int SHARE_QQ = 1;
    private static final int SHARE_WECHAT = 2;
    private static final int SHARE_WEIBO = 3;
    private static final int SHARE_QQZONE = 4;
    private static final int SHARE_MORE = 0;
}
