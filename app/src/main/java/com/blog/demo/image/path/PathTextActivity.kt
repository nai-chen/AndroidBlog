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

class PathTextActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(PathTextView(this))
    }

    private class PathTextView constructor(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
        private val mRedPaint: Paint = Paint()
        private val mBluePaint: Paint = Paint()

        init {
            mRedPaint.isAntiAlias = true
            mRedPaint.color = Color.RED
            mRedPaint.style = Paint.Style.STROKE
            mRedPaint.strokeWidth = 5f

            mBluePaint.isAntiAlias = true
            mBluePaint.color = Color.BLUE
            mBluePaint.style = Paint.Style.FILL
            mBluePaint.textSize = 50f
        }

        override fun onDraw(canvas: Canvas) {
            val path = Path()
            path.addOval(50f, 50f, 300f, 200f, Path.Direction.CW)
            canvas.drawPath(path, mRedPaint)
            canvas.drawTextOnPath("This is a text", path, 0f, 0f, mBluePaint)

            path.reset()
            path.addOval(400f, 50f, 650f, 200f, Path.Direction.CCW)
            canvas.drawPath(path, mRedPaint)
            canvas.drawTextOnPath("This is a text", path, 0f, 0f, mBluePaint)
        }

    }

}