package com.blog.demo.control.layout

import android.content.Context
import android.graphics.Canvas
import android.text.StaticLayout
import android.util.AttributeSet
import android.view.View
import com.blog.demo.LogTool

class StaticLayoutView (context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        View(context, attrs, defStyleAttr) {

    private var mStaticLayout: StaticLayout? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    fun setLayout(layout: StaticLayout) {
        mStaticLayout = layout
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var height = mStaticLayout?.height ?: 0

        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mStaticLayout?.draw(canvas)
    }

}