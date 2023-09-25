package com.example.eye_openingJava.util;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.eye_openingJava.R;
import com.example.eye_openingJava.extension.CharSequenceExt;

/**
 *@Author: create by boge
 *@Createtime: 2023/9/6 17:12
 *@Function:
 * 调用系统原生分享工具类
*/
public class ShareUtil {

    private static final int SHARE_QQ = 1;
    private static final int SHARE_WECHAT = 2;
    private static final int SHARE_WEIBO = 3;
    private static final int SHARE_QQZONE = 4;
    private static final int SHARE_MORE = 0;

    private static void processShare(Activity activity, String shareContent, int shareType) {
        switch (shareType) {
            case SHARE_QQ:
                if (!GlobalUtil.isQQInstalled(activity)) {
                    CharSequenceExt.showToast(GlobalUtil.getString(activity,R.string.your_phone_does_not_install_qq), Toast.LENGTH_SHORT);
                    return;
                }
                share(activity, shareContent, "com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");
                break;
            case SHARE_WECHAT:
                if (!GlobalUtil.isWechatInstalled(activity)) {
                    CharSequenceExt.showToast(GlobalUtil.getString(activity,R.string.your_phone_does_not_install_wechat), Toast.LENGTH_SHORT);
                    return;
                }
                share(activity, shareContent, "com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
                break;
            case SHARE_WEIBO:
                if (!GlobalUtil.isWeiboInstalled(activity)) {
                    CharSequenceExt.showToast(GlobalUtil.getString(activity,R.string.your_phone_does_not_install_weibo), Toast.LENGTH_SHORT);
                    return;
                }
                share(activity, shareContent, "com.sina.weibo", "com.sina.weibo.composerinde.ComposerDispatchActivity");
                break;
            case SHARE_QQZONE:
                if (!GlobalUtil.isWeiboInstalled(activity)) {
                    CharSequenceExt.showToast(GlobalUtil.getString(activity,R.string.your_phone_does_not_install_qq_zone), Toast.LENGTH_SHORT);
                    return;
                }
                share(activity, shareContent, "com.qzone", "com.qzonex.module.operation.ui.QZonePublishMoodActivity");
                break;
            case SHARE_MORE:
                share(activity, shareContent);
                break;
        }
    }

    private static void share(Activity activity, String shareContent, String packageName, String className) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareContent);
            shareIntent.setClassName(packageName, className);
            activity.startActivity(shareIntent);
        } catch (Exception e) {
            CharSequenceExt.showToast(GlobalUtil.getString(activity,R.string.share_unknown_error), Toast.LENGTH_SHORT);

        }
    }

    private static void share(Activity activity, String shareContent) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareContent);
        activity.startActivity(Intent.createChooser(shareIntent, GlobalUtil.getString(activity,R.string.share_to)));
    }

    /**
     * 调用系统原生分享
     *
     * @param shareContent 分享内容
     * @param shareType SHARE_MORE=0，SHARE_QQ=1，SHARE_WECHAT=2，SHARE_WEIBO=3，SHARE_QQZONE=4
     */
    public static void share(Activity activity, String shareContent, int shareType) {
        processShare(activity, shareContent, shareType);
    }
}
