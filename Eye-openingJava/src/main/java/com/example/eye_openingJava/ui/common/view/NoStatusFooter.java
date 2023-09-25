package com.example.eye_openingJava.ui.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.eye_openingJava.R;
import com.example.eye_openingJava.extension.DensityExt;
import com.example.eye_openingJava.util.TypeFaceUtil;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.simple.SimpleComponent;

public class NoStatusFooter extends SimpleComponent implements RefreshFooter {

    private String mTextNothing = ""; //没有更多数据了

    private TextView mTitleText;

    private int mFooterHeight = 0;

    private int mBackgroundColor = 0;

    private boolean mNoMoreData = false;

    private RefreshKernel mRefreshKernel;

    public NoStatusFooter(Context context){
        this(context,null,0);
    }
    public NoStatusFooter(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public NoStatusFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.layout_srl_classics_footer, this);
        mTitleText = findViewById(R.id.srl_classics_title);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.NoStatusFooter,0,0);
        if (typedArray != null) {
            if (typedArray.hasValue(R.styleable.NoStatusFooter_srlPrimaryColor)) {
                setPrimaryColor(typedArray.getColor(R.styleable.NoStatusFooter_srlPrimaryColor, 0));
            }
            if (typedArray.hasValue(R.styleable.NoStatusFooter_srlAccentColor)) {
                setAccentColor(typedArray.getColor(R.styleable.NoStatusFooter_srlAccentColor, 0));
            }
            if (typedArray.hasValue(R.styleable.NoStatusFooter_srlTextSizeTitle)) {
                mTitleText.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        typedArray.getDimensionPixelSize(R.styleable.NoStatusFooter_srlTextSizeTitle,
                                DensityExt.dp2px(context,16f)));
            }
            mTextNothing = typedArray.hasValue(R.styleable.NoStatusFooter_srlTextNothing)
                    ? typedArray.getString(R.styleable.NoStatusFooter_srlTextNothing)
                    : (REFRESH_FOOTER_NOTHING != null ? REFRESH_FOOTER_NOTHING : context.getString(R.string.srl_footer_nothing));
            mTitleText.setTypeface(TypefaceTextView.getTypeface(
                    typedArray.getInt(R.styleable.NoStatusFooter_srlTextTitleTypeface, TypeFaceUtil.LOBSTER_TYPEFACE)));
            typedArray.recycle();
        }
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
        mRefreshKernel = kernel;
        mRefreshKernel.requestDrawBackgroundFor(this,mBackgroundColor);
        if (mFooterHeight == 0) mFooterHeight = height;//获取SmartRefreshLayout全局设置的Footer高度。
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        super.onFinish(refreshLayout, success);
        return 0;
    }

    /**
     * 设置数据全部加载完成，将不能再次触发加载功能
     */
    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        if (mNoMoreData != noMoreData){
            mNoMoreData = noMoreData;
            refreshFooterHeight();
            if (noMoreData){
                mTitleText.setText(mTextNothing);
            }
        }
        return true;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        super.onStateChanged(refreshLayout, oldState, newState);
        if (!mNoMoreData){
            switch (newState){
                case None:
                    break;
                case PullUpToLoad:
                    refreshFooterHeight();
                    break;
                case Loading:
                case LoadReleased:
                    break;
                case ReleaseToLoad:
                    break;
                case Refreshing:
                    break;
                default:
                    break;
            }
        } else {
            refreshFooterHeight();
        }
    }

    private void refreshFooterHeight() {
        if (mNoMoreData) {
            mRefreshKernel.getRefreshLayout().setFooterHeightPx(mFooterHeight);
        } else {
            mRefreshKernel.getRefreshLayout().setFooterHeight(0f);
        }
        mRefreshKernel.requestRemeasureHeightFor(this);
    }

    public NoStatusFooter setTextTitleSize(float size) {
        mTitleText.setTextSize(size);
        mRefreshKernel.requestRemeasureHeightFor(this);
        return this;
    }

    public NoStatusFooter setTextTitleTypeface(Typeface tf) {
        mTitleText.setTypeface(tf);
        mRefreshKernel.requestRemeasureHeightFor(this);
        return this;
    }

    public NoStatusFooter setPrimaryColor(@ColorInt int primaryColor) {
        mBackgroundColor = primaryColor;
        mRefreshKernel.requestDrawBackgroundFor(this, primaryColor);
        return this;
    }

    public NoStatusFooter setAccentColor(@ColorInt int accentColor) {
        mTitleText.setTextColor(accentColor);
        return this;
    }

    public NoStatusFooter setAccentColorId(@ColorRes int colorId) {
        setAccentColor(ContextCompat.getColor(getContext(), colorId));
        return this;
    }

    public static String REFRESH_FOOTER_NOTHING = null;      //没有更多数据了
}
