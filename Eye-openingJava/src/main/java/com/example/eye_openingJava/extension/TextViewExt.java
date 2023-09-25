package com.example.eye_openingJava.extension;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.example.eye_openingJava.EyeopeningApplication;

public class TextViewExt {

    /**
     * 设置TextView图标
     *
     * @param textView     TextView
     * @param drawable     图标
     * @param iconWidth    图标宽dp：默认自动根据图标大小
     * @param iconHeight   图标高dp：默认自动根据图标大小
     * @param direction    图标方向，0左 1上 2右 3下 默认图标位于左侧0
     */
    public static void setDrawable(TextView textView, Drawable drawable, Float iconWidth, Float iconHeight, int direction){
        if (iconWidth != null && iconHeight != null){
            // 第一个0是距左边距离，第二个0是距上边距离，iconWidth、iconHeight 分别是长宽
            drawable.setBounds(0,0, DensityExt.dp2px(EyeopeningApplication.context,iconWidth), DensityExt.dp2px(EyeopeningApplication.context,iconHeight));
        }
        switch (direction){
            case 0:
                textView.setCompoundDrawables(drawable,null,null,null);
                break;
            case 1:
                textView.setCompoundDrawables(null, drawable, null, null);
                break;
            case 2:
                textView.setCompoundDrawables(null, null, drawable, null);
                break;
            case 3:
                textView.setCompoundDrawables(null, null, null, drawable);
                break;
            default:
                throw new NoSuchMethodError();
        }
    }

    /**
     * 设置TextView图标
     *
     * @param textView      TextView
     * @param lDrawable     左边图标
     * @param rDrawable     右边图标
     * @param lIconWidth    左边图标宽dp：默认自动根据图标大小
     * @param lIconHeight   左边图标高dp：默认自动根据图标大小
     * @param rIconWidth    右边图标宽dp：默认自动根据图标大小
     * @param rIconHeight   右边图标高dp：默认自动根据图标大小
     */
    public static void setDrawables(TextView textView, Drawable lDrawable, Drawable rDrawable, Float lIconWidth, Float lIconHeight, Float rIconWidth, Float rIconHeight) {
        if (lIconWidth != null && lIconHeight != null) {
            lDrawable.setBounds(0, 0, DensityExt.dp2px(EyeopeningApplication.context,lIconWidth), DensityExt.dp2px(EyeopeningApplication.context,lIconHeight));
        }
        if (rIconWidth != null && rIconHeight != null) {
            rDrawable.setBounds(0, 0, DensityExt.dp2px(EyeopeningApplication.context,rIconWidth), DensityExt.dp2px(EyeopeningApplication.context,rIconHeight));
        }
        textView.setCompoundDrawables(lDrawable, null, rDrawable, null);
    }
}
