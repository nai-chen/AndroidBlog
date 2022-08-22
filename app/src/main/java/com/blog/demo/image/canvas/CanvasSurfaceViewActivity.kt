package com.blog.demo.image.canvas

import android.app.Activity
import android.graphics.*
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.blog.demo.R

class CanvasSurfaceViewActivity : Activity() {

    private var mRedPaint: Paint = Paint()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_canvas_surface_view)

        val surfaceView: SurfaceView = findViewById(R.id.surface_view)
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                val canvas = holder.lockCanvas()
                canvas.drawColor(resources.getColor(R.color.colorProduct))
                draw(canvas)
                holder.unlockCanvasAndPost(canvas)
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
            }

        })

        mRedPaint.color = Color.RED
        mRedPaint.style = Paint.Style.FILL
    }

    private fun draw(canvas: Canvas) {
        drawClipPath(canvas, 0f, 50f, Region.Op.DIFFERENCE)
        drawClipPath(canvas, 300f, 50f, Region.Op.INTERSECT)
        drawClipPath(canvas, 0f, 250f, Region.Op.UNION)
        drawClipPath(canvas, 300f, 250f, Region.Op.XOR)
        drawClipPath(canvas, 0f, 450f, Region.Op.REVERSE_DIFFERENCE)
        drawClipPath(canvas, 300f, 450f, Region.Op.REPLACE)
    }

    private fun drawClipPath(canvas: Canvas, dx: Float, dy: Float, op: Region.Op) {
        canvas.save()
        canvas.translate(dx, dy)

        val path1 = Path()
        path1.addCircle(150f, 100f, 75f, Path.Direction.CW)
        canvas.clipPath(path1)

        val path2 = Path()
        path2.addCircle(250f, 100f, 75f, Path.Direction.CW)
        canvas.clipPath(path2, op)

        canvas.drawColor(Color.RED)

        canvas.restore()
    }

}