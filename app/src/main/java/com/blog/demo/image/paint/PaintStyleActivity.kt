package com.blog.demo.image.paint

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.View

class PaintStyleActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(PaintStyleView(this))
    }

    private inner class PaintStyleView(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
        private val mRedPaint: Paint = Paint()

        init {
            mRedPaint.color = Color.RED
            mRedPaint.style = Paint.Style.STROKE
            mRedPaint.isAntiAlias = true // 设置是否使用抗锯齿功能
            mRedPaint.strokeWidth = 20f
        }

        override fun onDraw(canvas: Canvas) {
            canvas.drawLine(50f, 50f, 400f, 50f, mRedPaint)

            mRedPaint.alpha = 100
            canvas.drawLine(50f, 100f, 400f, 100f, mRedPaint)

            mRedPaint.setARGB(10, 255, 0, 0)
            canvas.drawLine(50f, 150f, 400f, 150f, mRedPaint)

            mRedPaint.color = Color.RED
            mRedPaint.style = Paint.Style.STROKE
            canvas.drawCircle(150f, 300f, 75f, mRedPaint)

            mRedPaint.style = Paint.Style.FILL
            canvas.drawCircle(350f, 300f, 75f, mRedPaint)

            mRedPaint.style = Paint.Style.FILL_AND_STROKE
            canvas.drawCircle(550f, 300f, 75f, mRedPaint)
        }


    }
}