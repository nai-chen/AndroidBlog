package com.blog.demo.image.paint

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.View

class PaintStrokeActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(PaintStrokeView(this))
    }

    internal class PaintStrokeView (context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {

        private val mRedPaint: Paint = Paint()

        init {
            mRedPaint.color = Color.RED
            mRedPaint.style = Paint.Style.STROKE
            mRedPaint.isAntiAlias = true // 设置是否使用抗锯齿功能
            mRedPaint.strokeWidth = 5f
        }

        override fun onDraw(canvas: Canvas) {
            mRedPaint.strokeWidth = 5f
            mRedPaint.strokeCap = Paint.Cap.BUTT
            canvas.drawLine(50f, 50f, 400f, 50f, mRedPaint)

            mRedPaint.strokeWidth = 20f
            canvas.drawLine(50f, 100f, 400f, 100f, mRedPaint)

            mRedPaint.strokeCap = Paint.Cap.ROUND
            canvas.drawLine(50f, 150f, 400f, 150f, mRedPaint)

            mRedPaint.strokeCap = Paint.Cap.SQUARE
            canvas.drawLine(50f, 200f, 400f, 200f, mRedPaint)

            mRedPaint.strokeCap = Paint.Cap.ROUND
            mRedPaint.strokeJoin = Paint.Join.MITER
            canvas.drawRect(50f, 250f, 400f, 400f, mRedPaint)

            mRedPaint.strokeJoin = Paint.Join.ROUND
            canvas.drawRect(50f, 450f, 400f, 600f, mRedPaint)

            mRedPaint.strokeJoin = Paint.Join.BEVEL
            canvas.drawRect(50f, 650f, 400f, 800f, mRedPaint)
        }

    }

}