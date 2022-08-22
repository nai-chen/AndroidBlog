package com.blog.demo.image.path

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Path.FillType
import android.os.Bundle
import android.util.AttributeSet
import android.view.View

class PathFillTypeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(PathFillTypeView(this))
    }

    private inner class PathFillTypeView(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
        private val mRedPaint: Paint = Paint()
        private val mPath: Path = Path()

        init {
            mRedPaint.isAntiAlias = true
            mRedPaint.color = Color.RED
            mRedPaint.style = Paint.Style.FILL
            mRedPaint.strokeWidth = 5f

            mPath.addCircle(100f, 100f, 75f, Path.Direction.CCW)
            mPath.addCircle(150f, 150f, 75f, Path.Direction.CCW)
            mPath.addCircle(200f, 200f, 75f, Path.Direction.CCW)
        }

        override fun onDraw(canvas: Canvas) {
            showFillType(canvas, 50, 50, FillType.WINDING)
            showFillType(canvas, 400, 50, FillType.EVEN_ODD)
            showFillType(canvas, 50, 400, FillType.INVERSE_WINDING)
            showFillType(canvas, 400, 400, FillType.INVERSE_EVEN_ODD)
        }

        private fun showFillType(canvas: Canvas, dx: Int, dy: Int, ft: FillType) {
            canvas.save()

            canvas.translate(dx.toFloat(), dy.toFloat())
            canvas.clipRect(0, 0, 300, 300)
            canvas.drawColor(Color.WHITE)
            mPath.fillType = ft
            canvas.drawPath(mPath, mRedPaint)

            canvas.restore()
        }

    }

}