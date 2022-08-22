package com.blog.demo.image.path

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.util.AttributeSet
import android.view.View

class PathNormalActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(PathNormalView(this))
    }

    private inner class PathNormalView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

        private val mRedPaint: Paint = Paint()

        init {
            mRedPaint.isAntiAlias = true
            mRedPaint.color = Color.RED
            mRedPaint.style = Paint.Style.STROKE
            mRedPaint.strokeWidth = 5f
        }

        override fun onDraw(canvas: Canvas) {
            val path = Path()
            path.moveTo(50f, 50f)
            path.lineTo(200f, 100f)

            path.rMoveTo(50f, 0f)
            path.rLineTo(100f, 100f)

            path.addRect(50f, 250f, 300f, 350f, Path.Direction.CW)

            path.addRoundRect(50f, 400f, 300f, 500f, 20f, 10f, Path.Direction.CW)
            path.addRoundRect(400f, 400f, 650f, 500f,
                floatArrayOf(10f, 20f, 10f, 20f, 20f, 10f, 20f, 10f), Path.Direction.CW)

            path.addOval(50f, 550f, 300f, 700f, Path.Direction.CW)
            path.addCircle(525f, 625f, 75f, Path.Direction.CW)

            path.addArc(50f, 750f, 300f, 900f, 0f, 180f)
            path.addArc(400f, 750f, 650f, 900f, 90f, 225f)

            path.arcTo(50f, 950f, 300f, 1100f, 0f, 180f, true)
            path.arcTo(400f, 950f, 650f, 1100f, 90f, 225f, false)

            canvas.drawPath(path, mRedPaint)
        }

    }

}