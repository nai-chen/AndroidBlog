package com.blog.demo.image.canvas

import android.app.Activity
import android.graphics.*
import android.os.Bundle
import android.widget.ImageView
import com.blog.demo.R

class CanvasBitmapActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_canvas_bitmap)

        val imageView: ImageView = findViewById(R.id.image_view)
        val bitmap = Bitmap.createBitmap(600, 800, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        draw(canvas)
        imageView.setImageBitmap(bitmap)
    }

    private fun draw(canvas: Canvas) {
        val text = "This is a text"
        val redPaint = Paint()
        redPaint.color = Color.RED
        redPaint.style = Paint.Style.FILL
        redPaint.textSize = 20f
        redPaint.isAntiAlias = true // 设置是否使用抗锯齿功能
        canvas.drawText(text, 50f, 30f, redPaint)
        canvas.drawText(text, 2, 14, 50f, 55f, redPaint)

        val paint = Paint(redPaint)
        paint.style = Paint.Style.STROKE

        val path = Path()
        path.moveTo(50f, 100f)
        path.lineTo(300f, 200f)

        canvas.drawPath(path, paint)
        canvas.drawTextOnPath(text, path, 80f, 20f, redPaint)
    }

}