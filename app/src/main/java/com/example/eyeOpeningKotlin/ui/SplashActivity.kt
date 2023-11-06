package com.example.eyeOpeningKotlin.ui

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.lifecycle.lifecycleScope
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.databinding.ActivitySplashBinding
import com.example.eyeOpeningKotlin.ui.common.ui.BaseActivity
import com.example.eyeOpeningKotlin.util.DataStoreUtils
import com.example.eyeOpeningKotlin.util.GlobalUtil
import com.permissionx.guolindev.PermissionX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 闪屏页面，应用程序首次启动入口。
 *
 * @author driverSkr
 * @since  2023/10/19
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity: BaseActivity() {

    private var _binding: ActivitySplashBinding? = null

    //通过延迟初始化来获取 _binding 的值。
    // 这是一种常见的方式来避免空指针异常并确保视图绑定只在需要时进行初始化
    private val binding: ActivitySplashBinding get() = _binding!!

    //闪屏画面的显示时间
    private val splashDuration = 3 * 1000L

    //淡入动画
    //使用了 lazy 委托，这样它们只会在首次访问时进行初始化
    private val alphaAnimation by lazy {
        //指定透明度：0表示完全不透明，1表示完全透明
        AlphaAnimation(0.5f,1.0f).apply {
            //设置动画的持续时间
            duration = splashDuration
            //是否维持结束画面，true表示动画结束后停留在结束画面
            fillAfter = true
        }
    }

    //缩放动画
    private val scaleAnimation by lazy {
        //最终效果：使视图从其当前大小逐渐放大到 1.05 倍的大小，并以视图中心为基准点进行缩放
        /*参数1-4：横坐标放大0.05倍，纵坐标放大0.05倍
        * 参数5：定义了 X 轴上缩放的基准点类型（表示 pivotXValue 是相对于视图宽度的百分比，值在 0.0 到 1.0 之间）
        * 参数6：是 X 轴上的基准点值（表示相对于视图宽度或父容器宽度的百分比）
        * 参数7,8：在Y轴上...
        * */
        ScaleAnimation(1f,1.05f,1f,1.05f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
            .apply {
                //设置动画的持续时间
                duration = splashDuration
                //是否维持结束画面，true表示动画结束后停留在结束画面
                fillAfter = true
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //请求外部存储权限
        requestWriteExternalStoragePermission()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null //释放资源
    }

    //用于设置视图
    override fun setupViews() {
        super.setupViews()
        //启动两个动画
        binding.ivSlogan.startAnimation(alphaAnimation)
        binding.ivSplashPicture.startAnimation(scaleAnimation)
        //使用 lifecycleScope 启动了一个协程
        lifecycleScope.launch {
            //3秒后跳转 并结束当前 Activity
            delay(splashDuration)
            MainActivity.start(this@SplashActivity)
            finish()
        }
        //更改为不是首次登陆
        isFirstEntryApp = false
    }

    //请求外部存储权限
    private fun requestWriteExternalStoragePermission(){
        //关联权限请求与 Activity，指定需要请求的权限
        PermissionX.init(this@SplashActivity).permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            //用于处理当权限被拒绝时的逻辑
                /*scope 用于执行后续的操作，deniedList 包含被拒绝的权限列表*/
            .onExplainRequestReason { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_picture_processing)
                //显示一个权限请求理由对话框，解释为什么需要这个权限，并提供"确定"和"取消"按钮
                scope.showRequestReasonDialog(deniedList,message,GlobalUtil.getString(R.string.ok), GlobalUtil.getString(R.string.cancel))
            }
            //当用户选择前往系统设置来授予权限的情况
            .onForwardToSettings { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_picture_processing)
                //显示一个对话框，提示用户前往系统设置以授予权限
                scope.showForwardToSettingsDialog(deniedList, message, GlobalUtil.getString(R.string.settings), GlobalUtil.getString(R.string.cancel))
            }
            //权限请求的核心部分。它是一个请求权限的函数，当用户响应权限请求后，将会触发这个函数
            /*allGranted 是一个布尔值，表示是否所有权限都被授予，
            **grantedList 包含已经授予的权限，
            **deniedList 包含被拒绝的权限*/
            .request { _, _, _ ->
                requestReadPhoneStatePermission()
            }

    }

    //请求读取手机状态信息的权限
    private fun requestReadPhoneStatePermission(){
        PermissionX.init(this@SplashActivity).permissions(Manifest.permission.READ_PHONE_STATE)
            //当权限请求被拒绝时
            .onExplainRequestReason { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_access_phone_info)
                scope.showRequestReasonDialog(deniedList, message, GlobalUtil.getString(R.string.ok), GlobalUtil.getString(R.string.cancel))
            }
            //当用户选择前往系统设置来授予权限的情况
            .onForwardToSettings { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_access_phone_info)
                scope.showForwardToSettingsDialog(deniedList, message, GlobalUtil.getString(R.string.settings), GlobalUtil.getString(R.string.cancel))
            }
            //当用户响应权限请求后，将会触发这个函数
            .request { _, _, _ ->
                //绑定视图
                _binding = ActivitySplashBinding.inflate(layoutInflater)
                setContentView(binding.root)
            }
    }

    companion object{
        /**
         * 是否首次进入APP应用
         */
        var isFirstEntryApp: Boolean
            //get()是 isFirstEntryApp 属性的 getter 方法
            get() = DataStoreUtils.readBooleanData("is_first_entry_app", true)
            //set(value)是 isFirstEntryApp 属性的 setter 方法
            set(value) {
                CoroutineScope(Dispatchers.IO).launch { DataStoreUtils.saveBooleanData("is_first_entry_app", value) }
            }

    }
}