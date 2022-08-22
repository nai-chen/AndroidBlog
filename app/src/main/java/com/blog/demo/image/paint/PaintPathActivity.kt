package com.blog.demo.image.paint

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.util.AttributeSet
import android.view.View

class PaintPathActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(PaintPathView(this))
    }

    private inner class PaintPathView(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
        private val mPath = Path()
        private val mPaint = Paint()
        private var mPhase = 0

        init {
            mPath.moveTo(50f, 50f)
            for (index in 1..19) {
                mPath.lineTo((50 + 30 * index).toFloat(), 50 + (60 * Math.random()).toFloat())
            }
            mPaint.isAntiAlias = true
            mPaint.color = Color.BLACK
            mPaint.style = Paint.Style.STROKE
            mPaint.strokeWidth = 2f
        }

        override fun onDraw(canvas: Canvas) {
            val path = Path()
            path.addCircle(0f, 0f, 3f, Path.Direction.CW)

            val effects = arrayOf(CornerPathEffect(25f),
                DashPathEffect(floatArrayOf(20f, 5f, 10f, 5f), mPhase.toFloat()),
                PathDashPathEffect(path, 12f, mPhase.toFloat(), PathDashPathEffect.Style.ROTATE),
                DiscretePathEffect(3.0f, 5.0f),
                DiscretePathEffect(5.0f, 3.0f))
            drawPath(canvas, 0, null)
            drawPath(canvas, 80, effects[0])
            drawPath(canvas, 160, effects[1])
            drawPath(canvas, 240, effects[2])
            drawPath(canvas, 320, effects[3])
            drawPath(canvas, 400, effects[4])

            mPhase++
            mPhase %= 1000
            invalidate()
        }

        private fun drawPath(canvas: Canvas, dy: Int, effect: PathEffect?) {
            canvas.save()

            canvas.translate(0f, dy.toFloat())
            mPaint.pathEffect = effect
            canvas.drawPath(mPath, mPaint)

            canvas.restore()
        }

    }

}