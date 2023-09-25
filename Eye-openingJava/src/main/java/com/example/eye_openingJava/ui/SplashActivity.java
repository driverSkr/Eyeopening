package com.example.eye_openingJava.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eye_openingJava.R;
import com.example.eye_openingJava.databinding.ActivitySplashBinding;
import com.example.eye_openingJava.ui.common.ui.BaseActivity;
import com.example.eye_openingJava.util.GlobalUtil;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.ExplainReasonCallback;
import com.permissionx.guolindev.callback.ForwardToSettingsCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.request.ExplainScope;
import com.permissionx.guolindev.request.ForwardScope;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/**
 *@Author: create by boge
 *@Createtime: 2023/9/7 11:43
 *@Description: 闪屏页面，应用程序首次启动入口。
*/
@SuppressLint("CustomSplashScreen")
public class SplashActivity extends BaseActivity {

    private final Context mContext = this;

    private ActivitySplashBinding _binding;

    private ActivitySplashBinding getBinding(){
        return _binding;
    }

    private final long splashDuration = 3 * 1000L;

    private final AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f,1.0f);

    private final ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1.05f, 1f, 1.05f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWriteExternalStoragePermission();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _binding = null;
    }

    private void requestWriteExternalStoragePermission() {
        if (PermissionX.isGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            requestReadPhoneStatePermission();
        } else {
            PermissionX.init(this)
                    .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .onExplainRequestReason(new ExplainReasonCallback() {
                        @Override
                        public void onExplainReason(@NonNull ExplainScope scope, @NonNull List<String> deniedList) {
                            String message = GlobalUtil.getString(mContext, R.string.request_permission_picture_processing);
                            scope.showRequestReasonDialog(deniedList,message,GlobalUtil.getString(mContext,R.string.ok),GlobalUtil.getString(mContext,R.string.cancel));
                        }
                    })
                    .onForwardToSettings(new ForwardToSettingsCallback() {
                        @Override
                        public void onForwardToSettings(@NonNull ForwardScope scope, @NonNull List<String> deniedList) {
                            String message = GlobalUtil.getString(mContext,R.string.request_permission_picture_processing);
                            scope.showForwardToSettingsDialog(deniedList, message, GlobalUtil.getString(mContext,R.string.settings), GlobalUtil.getString(mContext,R.string.cancel));
                        }
                    })
                    .request(new RequestCallback() {
                        @Override
                        public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                            requestReadPhoneStatePermission();
                        }
                    });
        }
    }

    private void requestReadPhoneStatePermission() {
        if (PermissionX.isGranted(this,Manifest.permission.READ_PHONE_STATE)){
            _binding = ActivitySplashBinding.inflate(getLayoutInflater());
            setContentView(getBinding().getRoot());
        } else {
            PermissionX.init(this)
                    .permissions(Manifest.permission.READ_PHONE_STATE)
                    .onExplainRequestReason(new ExplainReasonCallback() {
                        @Override
                        public void onExplainReason(@NonNull ExplainScope scope, @NonNull List<String> deniedList) {
                            String message = GlobalUtil.getString(mContext,R.string.request_permission_access_phone_info);
                            scope.showRequestReasonDialog(deniedList, message, GlobalUtil.getString(mContext,R.string.ok), GlobalUtil.getString(mContext,R.string.cancel));
                        }
                    })
                    .onForwardToSettings(new ForwardToSettingsCallback() {
                        @Override
                        public void onForwardToSettings(@NonNull ForwardScope scope, @NonNull List<String> deniedList) {
                            String message = GlobalUtil.getString(mContext,R.string.request_permission_access_phone_info);
                            scope.showForwardToSettingsDialog(deniedList, message, GlobalUtil.getString(mContext,R.string.settings), GlobalUtil.getString(mContext,R.string.cancel));
                        }
                    })
                    .request(new RequestCallback() {
                        @Override
                        public void onResult(boolean allGranted, @NonNull List<String> grantedList, @NonNull List<String> deniedList) {
                            _binding = ActivitySplashBinding.inflate(getLayoutInflater());
                            setContentView(getBinding().getRoot());
                        }
                    });
        }
    }

    public static boolean isFirstEntryApp(){
        return DataStoreUtils.readBooleanData("is_first_entry_app", true);
    }

    public static void setIsFirstEntryApp(boolean value) {
        new CoroutineScope(Dispatchers.IO).launch(new Function1<CoroutineScope, Unit>() {
            @Override
            public Unit invoke(CoroutineScope scope) {
                DataStoreUtils.saveBooleanData("is_first_entry_app", value);
                return null;
            }
        });
    }
}