package com.blog.demo.image.canvas

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.View

class CanvasViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(CanvasView(this))
    }

    private inner class CanvasView(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
        private val mRedPaint: Paint = Paint()
        private val mBluePaint: Paint = Paint()

        init {
            mRedPaint.color = Color.RED
            mRedPaint.style = Paint.Style.STROKE
            mRedPaint.strokeWidth = 5f

            mBluePaint.color = Color.BLUE
            mBluePaint.style = Paint.Style.FILL
            mBluePaint.strokeWidth = 10f
        }

        override fun onDraw(canvas: Canvas) {
            canvas.drawPoint(100f, 50f, mRedPaint)

            var pts = floatArrayOf(100f, 75f, 150f, 100f, 200f, 125f)
            canvas.drawPoints(pts, mBluePaint)

            canvas.drawLine(100f, 200f, 400f, 200f, mRedPaint)

            pts = floatArrayOf(100f, 250f, 400f, 250f, 400f, 250f, 250f, 450f, 250f, 450f, 100f, 250f)
            canvas.drawLines(pts, mBluePaint)

            canvas.drawRect(100f, 500f, 300f, 600f, mRedPaint)
            canvas.drawRect(400f, 500f, 600f, 600f, mBluePaint)

            canvas.drawRoundRect(100f, 650f, 300f, 750f, 40f, 20f, mRedPaint)
            canvas.drawRoundRect(400f, 650f, 600f, 750f, 40f, 20f, mBluePaint)

            canvas.drawOval(100f, 800f, 300f, 900f, mRedPaint)
            canvas.drawOval(400f, 800f, 600f, 900f, mBluePaint)

            canvas.drawCircle(200f, 1000f, 50f, mRedPaint)
            canvas.drawCircle(500f, 1000f, 50f, mBluePaint)

            canvas.drawArc(100f, 1100f, 300f, 1200f, 45f, 225f, false, mRedPaint)
            canvas.drawArc(400f, 1100f, 600f, 1200f, 45f, 225f, true, mRedPaint)
            canvas.drawArc(100f, 1250f, 300f, 1350f, 45f, 225f, false, mBluePaint)
            canvas.drawArc(400f, 1250f, 600f, 1350f, 45f, 225f, true, mBluePaint)
        }

    }

}