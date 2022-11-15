package com.blog.demo.image.gif

import android.content.Context
import android.graphics.Canvas
import android.graphics.Movie
import android.graphics.Paint
import android.os.SystemClock
import android.util.AttributeSet
import android.view.View
import java.io.InputStream

class GifImageView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {

    private var mMovie: Movie? = null
    private var mMovieStart: Long = 0
    private var mStarted: Boolean = false
    private var mDuration: Int = 0
    private var mPaint: Paint = Paint()

    init {
        // 关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint)
    }

    constructor(context: Context): this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    fun setResource(inputStream: InputStream) {
        try {
            mMovie = Movie.decodeStream(inputStream)
            mDuration = mMovie?.duration() ?: 1000
        } catch (e: Exception) {
        }
    }

    fun start() {
        mMovieStart = SystemClock.elapsedRealtime()
        mStarted = true

        invalidate()
    }

    fun stop() {
        mMovieStart = 0
        mStarted = false
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        var movie = mMovie
        if (mStarted && movie != null) {
            var realTime = (SystemClock.elapsedRealtime()  - mMovieStart).toInt() % mDuration
            movie.setTime(realTime)

            var scale = movie.width() * 1.0f / width
            movie.draw(canvas, 0f,  (height - movie.height() * scale) / 2)

            invalidate()
        }

    }

}