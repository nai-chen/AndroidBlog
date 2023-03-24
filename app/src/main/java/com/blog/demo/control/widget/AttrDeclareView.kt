package com.blog.demo.control.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.blog.demo.R

class AttrDeclareView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        View(context, attrs, defStyleAttr) {

    private var mText: String? = null
    private var mPaint: Paint = Paint()

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AttrDeclareView)

        val color = a.getColor(R.styleable.AttrDeclareView_background_color, 0)
        mText = a.getString(R.styleable.AttrDeclareView_android_text)
        val textSize = a.getDimensionPixelSize(R.styleable.AttrDeclareView_android_textSize, 0)
        val textColor = a.getColor(R.styleable.AttrDeclareView_android_textColor, 0)

        a.recycle()

        if (color != 0) {
            setBackgroundColor(color)
        }

        if (mText != null) {
            mPaint.color = textColor
            mPaint.textSize = textSize.toFloat()
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (mText != null) {
            canvas.drawText(mText!!, 0f, (height / 2).toFloat(), mPaint)
        }
    }

}