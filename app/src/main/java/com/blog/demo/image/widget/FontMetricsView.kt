package com.blog.demo.image.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.blog.demo.LogTool
import com.blog.demo.R

class FontMetricsView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val LOG_TAG = "FontMetricsView"

    constructor(context: Context?) : this(context, null)

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        val text = "abfgABFG"
        val paint = Paint()
        paint.textSize = 100f

        val fm = paint.fontMetrics
        val width = paint.measureText(text)
        val beginX = 80f
        val endX = beginX * 2 + width
        val base = 200f
        canvas.drawText(text, beginX, base, paint)

        // base line
        canvas.drawLine(0f, base, endX, base, paint)
        LogTool.logi(LOG_TAG, "fm.top = " + fm.top)
        LogTool.logi(LOG_TAG, "fm.ascent = " + fm.ascent)
        LogTool.logi(LOG_TAG, "fm.bottom = " + fm.bottom)
        LogTool.logi(LOG_TAG, "fm.descent = " + fm.descent)

        // top
        val top = base + fm.top
        paint.color = resources.getColor(R.color.red)
        canvas.drawLine(0f, top, endX, top, paint)

        // ascent
        val ascent = base + fm.ascent
        paint.color = resources.getColor(R.color.green)
        canvas.drawLine(0f, ascent, endX, ascent, paint)

        // bottom
        val bottom = base + fm.bottom
        paint.color = resources.getColor(R.color.red)
        canvas.drawLine(0f, bottom, endX, bottom, paint)

        // ascent
        val descent = base + fm.descent
        paint.color = resources.getColor(R.color.green)
        canvas.drawLine(0f, descent, endX, descent, paint)

        val textPaint = Paint()
        textPaint.textSize = 36f

        canvas.drawText("base", beginX + width, base, textPaint)
        canvas.drawText("top", 40f, top, textPaint)
        canvas.drawText("ascent", width, ascent, textPaint)
        canvas.drawText("bottom", 40f, bottom, textPaint)
        canvas.drawText("descent", width, descent, textPaint)
    }
}