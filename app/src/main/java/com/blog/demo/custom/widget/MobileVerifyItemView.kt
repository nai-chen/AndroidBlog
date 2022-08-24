package com.blog.demo.custom.widget

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.blog.demo.R

class MobileVerifyItemView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    RelativeLayout(context, attrs, defStyleAttr) {

    private var tvNumber: TextView
    private var cursorView: View
    private var indicatorView: View

    private var alphaHolder: PropertyValuesHolder
    private var alphaAnimator: ObjectAnimator? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        inflate(context, R.layout.item_view_mobile_verify, this)

        tvNumber = findViewById(R.id.tv_number)
        cursorView = findViewById(R.id.view_cursor)
        indicatorView = findViewById(R.id.view_indicator)

        var keyframe1 = Keyframe.ofFloat(0f, 1f)
        var keyframe2 = Keyframe.ofFloat(0.4f, 1f)
        var keyframe3 = Keyframe.ofFloat(0.45f, 0f)
        var keyframe4 = Keyframe.ofFloat(1f, 0f)
        alphaHolder = PropertyValuesHolder.ofKeyframe("alpha", keyframe1, keyframe2, keyframe3, keyframe4)
    }

    fun setText(text: String) {
        tvNumber.text = text
    }

    fun setFocus(focus: Boolean) {
        // 获取焦点，显示光标
        if (focus) {
            cursorView.visibility = View.VISIBLE

            alphaAnimator?.cancel()

            alphaAnimator = ObjectAnimator.ofPropertyValuesHolder(cursorView, alphaHolder)
            alphaAnimator?.duration = 1500
            alphaAnimator?.repeatCount = -1
            alphaAnimator?.start()
        } else {
            cursorView.visibility = View.INVISIBLE
            alphaAnimator?.cancel()
            alphaAnimator = null
        }
        indicatorView.isSelected = focus
    }

}