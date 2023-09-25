package com.example.eye_openingJava.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import java.util.Locale;

/**
 *@Author: create by boge
 *@Createtime: 2023/9/6 15:55
 *@Function:
 * 应用程序全局的通用工具类，功能比较单一，经常被复用的功能，应该封装到此工具类当中，从而给全局代码提供方面的操作。
*/
public class GlobalUtil {

    private static final String TAG = "GlobalUtil";

    //获取当前应用程序的包名
    public static String getAppPackage(Context context) {
        return context.getPackageName();
    }

    //获取当前应用程序的名称
    public static String getAppName(Context context) {
        return context.getResources().getString(context.getApplicationInfo().labelRes);
    }

    //获取当前应用程序的版本名
    public static String getAppVersionName(Context context) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(getAppPackage(context), 0);
        return packageInfo.versionName;
    }

    //获取当前应用程序的版本号
    public static long getAppVersionCode(Context context) throws PackageManager.NameNotFoundException {
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(getAppPackage(context), 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return packageInfo.getLongVersionCode();
        } else {
            return packageInfo.versionCode;
        }
    }

    //获取开眼应用程序的版本名
    public static String getEyeopeningVersionName() {
        return "6.3.1";
    }

    //获取开眼应用程序的版本号
    public static long getEyeopeningVersionCode() {
        return 6030012;
    }

    //获取设备的型号，如果无法获取到，则返回Unknown。
    public static String getDeviceModel() {
        String deviceModel = Build.MODEL;
        if (TextUtils.isEmpty(deviceModel)) {
            deviceModel = "unknown";
        }
        return deviceModel;
    }

    //获取设备的品牌，如果无法获取到，则返回Unknown。
    public static String getDeviceBrand() {
        String deviceBrand = Build.BRAND;
        if (TextUtils.isEmpty(deviceBrand)) {
            deviceBrand = "unknown";
        }
        return deviceBrand.toLowerCase(Locale.getDefault());
    }

    private static String deviceSerial = null;

    /*获取设备的序列号。如果无法获取到设备的序列号，则会生成一个随机的UUID来作为设备的序列号，UUID生成之后会存入缓存，
     * 下次获取设备序列号的时候会优先从缓存中读取。*/
    /*@SuppressLint("HardwareIds")
    public static String getDeviceSerial(Context context) {
        if (deviceSerial == null) {
            String deviceId = null;
            String appChannel = getApplicationMetaData(context, "APP_CHANNEL");
            if (!"google".equals(appChannel) || !"samsung".equals(appChannel)) {
                try {
                    deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                } catch (Exception e) {
                    // 处理获取 android_id 的异常
                }
                if (!TextUtils.isEmpty(deviceId) && deviceId.length() < 255) {
                    deviceSerial = deviceId;
                    return deviceSerial;
                }
            }
            String uuid = DataStoreUtils.readStringData(context, "uuid", "");
            if (!TextUtils.isEmpty(uuid)) {
                deviceSerial = uuid;
                return deviceSerial;
            }
            uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase(Locale.getDefault());
            DataStoreUtils.saveStringData(context, "uuid", uuid);
            deviceSerial = uuid;
            return deviceSerial;
        } else {
            return deviceSerial;
        }
    }*/

    //获取资源文件中定义的字符串
    public static String getString(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    //获取资源文件中定义的尺寸
    public static int getDimension(Context context, int resId) {
        return context.getResources().getDimensionPixelOffset(resId);
    }

    //获取指定资源名的资源id
    public static int getResourceId(Context context, String name, String type) {
        return context.getResources().getIdentifier(name, type, getAppPackage(context));
    }

    //获取 AndroidManifest.xml 文件中，<application> 标签下的 meta-data 值
    public static String getApplicationMetaData(Context context, String key) {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(getAppPackage(context), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            // 处理获取 ApplicationInfo 的异常
        }
        if (applicationInfo == null) return "";
        if (applicationInfo.metaData != null) {
            return applicationInfo.metaData.getString(key);
        }
        return "";
    }

    //判断某个应用是否安装
    public static boolean isInstalled(Context context, String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            // 处理获取 PackageInfo 的异常
        }
        return packageInfo != null;
    }

    //获取当前应用程序的图标
    public static Drawable getAppIcon(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(getAppPackage(context), 0);
        } catch (PackageManager.NameNotFoundException e) {
            // 处理获取 ApplicationInfo 的异常
        }
        if (applicationInfo != null) {
            return packageManager.getApplicationIcon(applicationInfo);
        }
        return null;
    }

    //判断手机是否安装了 QQ
    public static boolean isQQInstalled(Context context) {
        return isInstalled(context, "com.tencent.mobileqq");
    }

    //判断手机是否安装了微信
    public static boolean isWechatInstalled(Context context) {
        return isInstalled(context, "com.tencent.mm");
    }

    //判断手机是否安装了微博
    public static boolean isWeiboInstalled(Context context) {
        return isInstalled(context, "com.sina.weibo");
    }
}
