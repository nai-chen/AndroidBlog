package com.blog.demo.image.canvas

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.util.AttributeSet
import android.view.View

class CanvasTranslationActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(CanvasTranslationView(this))
    }

    internal class CanvasTranslationView(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
        private val mRedPaint: Paint = Paint()
        private val mBluePaint: Paint = Paint()
        private val mGreenPaint: Paint = Paint()

        init {
            mRedPaint.color = Color.RED
            mRedPaint.isAntiAlias = true
            mRedPaint.style = Paint.Style.FILL

            mBluePaint.color = Color.BLUE
            mBluePaint.alpha = 100

            mGreenPaint.color = Color.GREEN
            mGreenPaint.alpha = 50
        }

        override fun onDraw(canvas: Canvas) {
            val rect = Rect(0, 0, 250, 100)

            canvas.save()
            canvas.translate(100f, 100f)
            canvas.drawRect(rect, mRedPaint)
            canvas.translate(50f, 50f)
            canvas.drawRect(rect, mBluePaint)
            canvas.restore()

            canvas.save()
            canvas.translate(600f, 100f)
            canvas.drawRect(rect, mRedPaint)
            canvas.rotate(45f)
            canvas.drawRect(rect, mBluePaint)
            canvas.restore()

            canvas.save()
            canvas.translate(600f, 100f)
            canvas.rotate(90f, 125f, 50f)
            canvas.drawRect(rect, mGreenPaint)
            canvas.restore()

            canvas.save()
            canvas.translate(100f, 500f)
            canvas.drawRect(rect, mRedPaint)
            canvas.skew(1f, 0f)
            canvas.drawRect(rect, mBluePaint)
            canvas.restore()

            canvas.save()
            canvas.translate(100f, 500f)
            canvas.skew(0f, 1f)
            canvas.drawRect(rect, mGreenPaint)
            canvas.restore()

            canvas.save()
            canvas.translate(600f, 500f)
            canvas.drawRect(rect, mRedPaint)
            canvas.scale(0.5f, 2f)
            canvas.drawRect(rect, mBluePaint)
            canvas.restore()
        }

    }
}