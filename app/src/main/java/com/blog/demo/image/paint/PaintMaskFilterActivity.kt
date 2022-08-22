package com.blog.demo.image.paint

import android.app.Activity
import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.View

class PaintMaskFilterActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(PaintMaskFilterView(this))
    }

    private inner class PaintMaskFilterView(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
        private val mPaint = Paint()

        init {
            mPaint.style = Paint.Style.FILL
            mPaint.color = Color.RED
            setLayerType(LAYER_TYPE_SOFTWARE, null)
        }

        override fun onDraw(canvas: Canvas) {
            mPaint.maskFilter = null
            canvas.drawRect(50f, 50f, 300f, 200f, mPaint)

            mPaint.maskFilter = BlurMaskFilter(10f, BlurMaskFilter.Blur.NORMAL)
            canvas.drawRect(50f, 300f, 300f, 450f, mPaint)

            mPaint.maskFilter = BlurMaskFilter(10f, BlurMaskFilter.Blur.SOLID)
            canvas.drawRect(400f, 300f, 650f, 450f, mPaint)

            mPaint.maskFilter = BlurMaskFilter(10f, BlurMaskFilter.Blur.OUTER)
            canvas.drawRect(50f, 550f, 300f, 700f, mPaint)

            mPaint.maskFilter = BlurMaskFilter(10f, BlurMaskFilter.Blur.INNER)
            canvas.drawRect(400f, 550f, 650f, 700f, mPaint)
        }

    }

}