package com.example.eye_openingJava.util;

import android.content.Context;
import android.graphics.Typeface;

public class TypeFaceUtil {

    public static final int FZLL_TYPEFACE = 1;
    public static final int FZDB1_TYPEFACE = 2;
    public static final int FUTURA_TYPEFACE = 3;
    public static final int DIN_TYPEFACE = 4;
    public static final int LOBSTER_TYPEFACE = 5;

    public static Typeface getFontTypeface(Context context, int typeface) {
        switch (typeface) {
            case FZLL_TYPEFACE:
                return Typeface.createFromAsset(context.getAssets(), "fonts/FZLanTingHeiS-L-GB-Regular.TTF");
            case FZDB1_TYPEFACE:
                return Typeface.createFromAsset(context.getAssets(), "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF");
            case FUTURA_TYPEFACE:
                return Typeface.createFromAsset(context.getAssets(), "fonts/Futura-CondensedMedium.ttf");
            case DIN_TYPEFACE:
                return Typeface.createFromAsset(context.getAssets(), "fonts/DIN-Condensed-Bold.ttf");
            case LOBSTER_TYPEFACE:
                return Typeface.createFromAsset(context.getAssets(), "fonts/Lobster-1.4.otf");
            default:
                return Typeface.DEFAULT;
        }
    }
}
