package com.example.eyeOpeningKotlin.ui.common.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.eyeOpeningKotlin.R
import com.example.eyeOpeningKotlin.util.TypeFaceUtil

/**
 * 带有自定义字体TextView。
 *
 * @author driverSkr
 * @since  2023/10/18
 */
class TypefaceTextView: AppCompatTextView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.TypefaceTextView, 0, 0)
            val typefaceType = typedArray.getInt(R.styleable.TypefaceTextView_typeface, 0)
            typeface = getTypeface(typefaceType)
            typedArray.recycle()
        }
    }

    companion object{
        /**
         * 根据字体类型，获取自定义字体。
         */
        fun getTypeface(typefaceType: Int?): Typeface = when(typefaceType){
            TypeFaceUtil.FZLL_TYPEFACE -> TypeFaceUtil.fzlLTypeface
            TypeFaceUtil.FZDB1_TYPEFACE -> TypeFaceUtil.fzdb1Typeface
            TypeFaceUtil.FUTURA_TYPEFACE -> TypeFaceUtil.futuraTypeface
            TypeFaceUtil.DIN_TYPEFACE -> TypeFaceUtil.dinTypeface
            TypeFaceUtil.LOBSTER_TYPEFACE -> TypeFaceUtil.lobsterTypeface
            else -> Typeface.DEFAULT
        }
    }
}