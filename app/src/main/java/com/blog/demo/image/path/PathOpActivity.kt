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

class PathOpActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(PathOpView(this))
    }

    private inner class PathOpView (context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
        private val mRedPaint: Paint = Paint()

        init {
            mRedPaint.isAntiAlias = true
            mRedPaint.color = Color.RED
            mRedPaint.style = Paint.Style.FILL
            mRedPaint.strokeWidth = 5f
        }

        override fun onDraw(canvas: Canvas) {
            drawOp(canvas, 50f, 50f, Path.Op.DIFFERENCE)
            drawOp(canvas, 350f, 50f, Path.Op.INTERSECT)
            drawOp(canvas, 50f, 250f, Path.Op.UNION)
            drawOp(canvas, 350f, 250f, Path.Op.XOR)
            drawOp(canvas, 50f, 450f, Path.Op.REVERSE_DIFFERENCE)
        }

        private fun drawOp(canvas: Canvas, dx: Float, dy: Float, op: Path.Op) {
            canvas.save()
            canvas.translate(dx, dy)
            val path1 = Path()
            path1.addCircle(100f, 100f, 75f, Path.Direction.CW)
            val path2 = Path()
            path2.addCircle(200f, 100f, 75f, Path.Direction.CW)
            path1.op(path2, op)
            canvas.drawPath(path1, mRedPaint)
            canvas.restore()
        }

    }

}