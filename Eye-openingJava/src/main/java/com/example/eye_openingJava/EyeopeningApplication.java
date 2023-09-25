package com.example.eye_openingJava;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;

import com.example.eye_openingJava.ui.common.view.NoStatusFooter;
import com.example.eye_openingJava.util.GlobalUtil;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshInitializer;

/**
 *@Author: create by boge
 *@Createtime: 2023/9/6 14:41
 *@Function:
 * 自定义Application，在这里进行全局的初始化操作。
*/
public class EyeopeningApplication extends Application {

    // 静态上下文变量
    public static Context context;

    //static 代码段可以防止内存泄露
    static {
        // 设置 SmartRefreshLayout 默认的刷新初始化器
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setEnableLoadMore(true);
                layout.setEnableLoadMoreWhenContentNotFull(true);
            }
        });
        // 设置 SmartRefreshLayout 默认的刷新头部创建器
        /*全局的Header构建器*/
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setEnableHeaderTranslationContent(true);
                MaterialHeader materialHeader = new MaterialHeader(context);
                materialHeader.setColorSchemeResources(R.color.blue,R.color.blue,R.color.blue);
                return materialHeader;
            }
        });
        // 设置 SmartRefreshLayout 默认的刷新底部创建器
        /*全局的Footer构建器*/
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setEnableFooterFollowWhenNoMoreData(true);
                layout.setEnableFooterTranslationContent(true);
                layout.setFooterHeight(153);
                layout.setFooterTriggerRate(0.6f);
                NoStatusFooter noStatusFooter = new NoStatusFooter(context);
                NoStatusFooter.REFRESH_FOOTER_NOTHING = GlobalUtil.getString(context,R.string.footer_not_more);
                noStatusFooter.setAccentColorId(R.color.colorTextPrimary);
                noStatusFooter.setTextTitleSize(16);
                return noStatusFooter;
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        // 设置 IjkPlayerManager 的日志级别
        //IjkPlayerManager.setLogLevel(BuildConfig.DEBUG ? IjkMediaPlayer.IJK_LOG_WARN : IjkMediaPlayer.IJK_LOG_SILENT);

        // 预先创建 WebViewActivity 的默认 URL 会话
        //WebViewActivity.DEFAULT_URL.preCreateSession();

        // 检查是否需要显示评价提示对话框并启动工作管理器
        /*if (!SplashActivity.isFirstEntryApp && DialogAppraiseTipsWorker.isNeedShowDialog) {
            WorkManager.getInstance(this).enqueue(DialogAppraiseTipsWorker.showDialogWorkRequest);
        }*/
    }
}
