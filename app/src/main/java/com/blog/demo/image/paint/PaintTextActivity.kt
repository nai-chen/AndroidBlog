package com.blog.demo.image.paint

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.util.AttributeSet
import android.view.View

class PaintTextActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(PaintTextView(this))
    }

    private inner class PaintTextView(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
        private val mRedPaint: Paint = Paint()
        private val typeface: Typeface

        init {
            mRedPaint.color = Color.RED
            mRedPaint.style = Paint.Style.FILL
            mRedPaint.isAntiAlias = true // 设置是否使用抗锯齿功能
            mRedPaint.textSize = 50f

            typeface = Typeface.createFromAsset(assets, "fonts/digital-7.ttf")
        }

        override fun onDraw(canvas: Canvas) {
            val text = "This is a text"
            canvas.drawText(text, 50f, 80f, mRedPaint)

            var paint = Paint(mRedPaint)
            paint.textSize = 30f
            canvas.drawText(text, 50f, 120f, paint)

            paint = Paint(mRedPaint)
            paint.isFakeBoldText = true // 设置文本仿粗体
            canvas.drawText(text, 50f, 180f, paint)

            paint = Paint(mRedPaint)
            paint.isUnderlineText = true // 设置文字的下划线
            canvas.drawText(text, 50f, 240f, paint)

            paint = Paint(mRedPaint)
            paint.textSkewX = -0.25f // 设置斜体字，值为负右倾值为正左倾
            canvas.drawText(text, 50f, 300f, paint)
            paint.textSkewX = 0.25f
            canvas.drawText(text, 50f, 360f, paint)

            paint = Paint(mRedPaint)
            paint.isStrikeThruText = true // 设置文本删除线
            canvas.drawText(text, 50f, 420f, paint)

            paint = Paint(mRedPaint)
            paint.textScaleX = 2f // 文本沿X轴水平缩放，默认值为1
            canvas.drawText(text, 50f, 480f, paint)

            paint = Paint(mRedPaint)
            paint.letterSpacing = 0.1f // 设置行的间距
            canvas.drawText(text, 50f, 540f, paint)

            paint = Paint(mRedPaint)
            paint.setShadowLayer(5f, 5f, 5f, Color.BLACK)
            canvas.drawText(text, 50f, 600f, paint)

            paint = Paint(mRedPaint)
            paint.typeface = typeface // 设置文本字体样式
            canvas.drawText(text, 50f, 660f, paint)

            paint = Paint(mRedPaint)
            paint.textAlign = Paint.Align.LEFT
            canvas.drawText(text, 400f, 720f, paint)

            paint.textAlign = Paint.Align.RIGHT
            canvas.drawText(text, 400f, 780f, paint)

            paint.textAlign = Paint.Align.CENTER
            canvas.drawText(text, 400f, 840f, paint)

            paint.strokeWidth = 5f
            canvas.drawLine(400f, 660f, 400f, 860f, paint)
        }

    }

}