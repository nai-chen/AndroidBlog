package com.blog.demo.control.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.blog.demo.R
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

class GestureView (context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        View(context, attrs, defStyleAttr) {
    private val INVALID_COUNT = 4

    enum class Status {
        Normal, Error
    }

    private var mNormalBmp: Bitmap
    private var mClickBmp: Bitmap
    private var mErrorBmp: Bitmap
    private var mNormalColor: Int
    private var mErrorColor: Int
    private var mBitmapPaint: Paint = Paint()
    private var mLinePaint: Paint = Paint()

    private val mTotalCircles: MutableList<CircleArea> = mutableListOf()
    private val mLinkCircle: MutableList<CircleArea> = mutableListOf()

    private var mCurrentPoint: PointF? = null
    private var mStatus = Status.Normal

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        mNormalBmp = BitmapFactory.decodeResource(resources, R.drawable.gesture_circle_normal)
        mClickBmp = BitmapFactory.decodeResource(resources, R.drawable.gesture_circle_click)
        mErrorBmp = BitmapFactory.decodeResource(resources, R.drawable.gesture_circle_error)

        mNormalColor = resources.getColor(R.color.gesture_normal_line)
        mErrorColor = resources.getColor(R.color.gesture_error_line)

        mBitmapPaint.isFilterBitmap = true

        mLinePaint.strokeWidth = 10f
        mLinePaint.isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val measuredWidth = measuredWidth
        val measuredHeight = measuredHeight
        val squareWidth = min(measuredWidth, measuredHeight)

        // 界面被3等分，每个手势占1/6
        val radius = squareWidth / 12.0f
        val startX = (measuredWidth - squareWidth) / 2.0f
        val startY = (measuredHeight - squareWidth) / 2.0f
        mTotalCircles.clear()
        val circleArea = CircleArea(startX + radius * 2, startY + radius * 2, radius, 1)
        mTotalCircles.add(circleArea)
        mTotalCircles.add(circleArea.moveTo(radius * 4, 0f, 2))
        mTotalCircles.add(circleArea.moveTo(radius * 8, 0f, 3))
        mTotalCircles.add(circleArea.moveTo(0f, radius * 4, 4))
        mTotalCircles.add(circleArea.moveTo(radius * 4, radius * 4, 5))
        mTotalCircles.add(circleArea.moveTo(radius * 8, radius * 4, 6))
        mTotalCircles.add(circleArea.moveTo(0f, radius * 8, 7))
        mTotalCircles.add(circleArea.moveTo(radius * 4, radius * 8, 8))
        mTotalCircles.add(circleArea.moveTo(radius * 8, radius * 8, 9))
        val width = (radius * 2).toInt()
        mNormalBmp = Bitmap.createScaledBitmap(mNormalBmp, width, width, false)
        mClickBmp = Bitmap.createScaledBitmap(mClickBmp, width, width, false)
        mErrorBmp = Bitmap.createScaledBitmap(mErrorBmp, width, width, false)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(resources.getColor(R.color.black))

        // 如果选择列表不为空
        if (mLinkCircle.size > 0) {
            // 设置连接线的颜色
            if (mStatus == Status.Normal) {
                mLinePaint.color = mNormalColor
            } else {
                mLinePaint.color = mErrorColor
            }

            // 手势键盘之间的连接线
            var lastCircle = mLinkCircle[0]
            for (i in 1 until mLinkCircle.size) {
                val circle = mLinkCircle[i]
                canvas.drawLine(lastCircle.x, lastCircle.y, circle.x, circle.y, mLinePaint)
                lastCircle = circle
            }
            // 手势键盘和当前点之间的连接线
            var currentPoint = mCurrentPoint
            if (currentPoint != null && mStatus == Status.Normal) {
                canvas.drawLine(lastCircle.x, lastCircle.y, currentPoint.x, currentPoint.y, mLinePaint)
            }
        }
        for (circle in mTotalCircles) {
            drawCircle(canvas, circle)
        }
    }

    private fun drawCircle(canvas: Canvas, circle: CircleArea) {
        var circleBitmap = mNormalBmp
        if (mLinkCircle.contains(circle)) {
            circleBitmap = if (mStatus == Status.Normal) {
                mClickBmp
            } else {
                mErrorBmp
            }
        }
        // 不同状态下绘制不同的图片。
        canvas.drawBitmap(circleBitmap, circle.x - circle.radius, circle.y - circle.radius, mBitmapPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // 只有在正常状态下，触摸才生效
        if (mStatus == Status.Normal) {
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> { // 记录手势
                    moveTo(event.x, event.y)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    moveTo(event.x, event.y)

                    // 如果小于有效数量
                    if (mLinkCircle.size < INVALID_COUNT) {
                        Toast.makeText(context, "InvalidCount " + mLinkCircle.size, Toast.LENGTH_LONG).show()

                        // 错误状态，界面不可点
                        mStatus = Status.Error

                        // 2秒后清空
                        postDelayed({
                            clearCircle()
                            postInvalidate()
                        }, 2000)
                    } else {
                        Toast.makeText(context, "Success " + getLinkText(), Toast.LENGTH_LONG).show()

                        clearCircle()
                    }
                }
            }
            postInvalidate()
        }
        return true
    }

    private fun moveTo(x: Float, y: Float) {
        // 当前触摸点
        mCurrentPoint = PointF(x, y)

        // 如果不在选择列表中，加入选择列表
        for (circle in mTotalCircles) {
            if (circle.contain(x, y) && !mLinkCircle.contains(circle)) {
                if (!mLinkCircle.contains(circle)) {
                    mLinkCircle.add(circle)
                }
                return
            }
        }
    }

    private fun getLinkText(): String {
        var text = ""
        for (circle in mLinkCircle) {
            text += circle.value
        }
        return text
    }

    private fun clearCircle() {
        mLinkCircle.clear()
        mCurrentPoint = null
        mStatus = Status.Normal
    }

    private class CircleArea(val x: Float, val y: Float, val radius: Float, val value: Int) {

        fun moveTo(dx: Float, dy: Float, value: Int): CircleArea {
            return CircleArea(x + dx, y + dy, radius, value)
        }

        // 该点是否在圆内
        fun contain(x: Float, y: Float): Boolean {
            return sqrt((this.x - x).toDouble().pow(2.0) + (this.y - y).toDouble().pow(2.0)) < radius
        }

    }

}