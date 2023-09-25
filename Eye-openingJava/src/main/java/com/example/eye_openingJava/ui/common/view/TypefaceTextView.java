package com.example.eye_openingJava.ui.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.eye_openingJava.R;
import com.example.eye_openingJava.util.TypeFaceUtil;

/**
 *@Author: create by boge
 *@Createtime: 2023/9/6 16:26
 *@Function:
 * 带有自定义字体TextView。
*/
public class TypefaceTextView extends AppCompatTextView {

    private static Context mContext;

    public TypefaceTextView(Context context) {
        this(context, null);
        mContext = context;
    }

    public TypefaceTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
    }

    public TypefaceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TypefaceTextView, 0, 0);
            int typefaceType = typedArray.getInt(R.styleable.TypefaceTextView_typeface, 0);
            setTypeface(getTypeface(typefaceType));
            typedArray.recycle();
        }

    }

    /**
     * 根据字体类型，获取自定义字体。
     */
    public static Typeface getTypeface(Integer typefaceType) {
        switch (typefaceType) {
            case TypeFaceUtil.FZLL_TYPEFACE:
                return TypeFaceUtil.getFontTypeface(mContext,TypeFaceUtil.FZLL_TYPEFACE);
            case TypeFaceUtil.FZDB1_TYPEFACE:
                return TypeFaceUtil.getFontTypeface(mContext,TypeFaceUtil.FZDB1_TYPEFACE);
            case TypeFaceUtil.FUTURA_TYPEFACE:
                return TypeFaceUtil.getFontTypeface(mContext,TypeFaceUtil.FUTURA_TYPEFACE);
            case TypeFaceUtil.DIN_TYPEFACE:
                return TypeFaceUtil.getFontTypeface(mContext,TypeFaceUtil.DIN_TYPEFACE);
            case TypeFaceUtil.LOBSTER_TYPEFACE:
                return TypeFaceUtil.getFontTypeface(mContext,TypeFaceUtil.LOBSTER_TYPEFACE);
            default:
                return Typeface.DEFAULT;
        }
    }
}
