package com.blog.demo.custom.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.blog.demo.R

class FlowLayout(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        ViewGroup(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        var height = 0
        val count = childCount

        var startX = 0
        var childHeight = 0

        for (index in 0 until count) {
            val child = getChildAt(index)
            if (child.visibility != GONE) {
                // 计算child宽高
                measureChild(child, widthMeasureSpec, heightMeasureSpec)

                // 如果超过一行，换行重新开始
                if (startX + child.measuredWidth > width) {
                    height += childHeight
                    childHeight = child.measuredHeight
                    startX = child.measuredWidth
                } else {
                    childHeight = Math.max(childHeight, child.measuredHeight)
                    startX += child.measuredWidth
                }
            }
        }

        height += childHeight
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY))
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val count = childCount
        var startX = 0
        var height = 0
        val width = right - left

        var childHeight = 0
        var startIndex = 0

        for (index in 0 until count) {
            val child = getChildAt(index)
            if (child.visibility != GONE) {
                // 如果超过一行，换行显示
                if (startX + child.measuredWidth > width) {
                    if (index > startIndex) {
                        layoutChildren(height, childHeight, startIndex, index)
                    }
                    startIndex = index
                    height += childHeight
                    startX = child.measuredWidth
                    childHeight = child.measuredHeight
                } else {
                    childHeight = Math.max(childHeight, child.measuredHeight)
                    startX += child.measuredWidth
                }
            }
        }

        if (startIndex < count) {
            layoutChildren(height, childHeight, startIndex, count)
        }

    }

    private fun layoutChildren(top: Int, childHeight: Int, startIndex: Int, endIndex: Int) {
        var startX = 0
        for (index in startIndex until endIndex) {
            val child = getChildAt(index)
            if (child.visibility != GONE) {
                val lp = child.layoutParams as LayoutParams
                var offsetY = 0
                if (lp.gravity == LayoutParams.GRAVITY_MIDDLE) {
                    offsetY = (childHeight - child.measuredHeight) / 2
                } else if (lp.gravity == LayoutParams.GRAVITY_BOTTOM) {
                    offsetY = childHeight - child.measuredHeight
                }
                child.layout(startX, top + offsetY, startX + child.measuredWidth,
                    top + offsetY + child.measuredHeight)
                startX += child.measuredWidth
            }
        }

    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams?): Boolean {
        return p != null && p is LayoutParams
    }

    override fun generateDefaultLayoutParams(): LayoutParams? {
        return LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun generateLayoutParams(p: ViewGroup.LayoutParams?): LayoutParams? {
        return LayoutParams(p)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams? {
        return LayoutParams(context, attrs)
    }

    class LayoutParams : ViewGroup.LayoutParams {
        var gravity = GRAVITY_TOP

        constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout_Layout)
            gravity = a.getInt(R.styleable.FlowLayout_Layout_gravity, GRAVITY_TOP)
            a.recycle()
        }

        constructor(width: Int, height: Int) : super(width, height) {}
        constructor(source: ViewGroup.LayoutParams?) : super(source) {
            if (source is LayoutParams) {
                gravity = source.gravity
            }
        }

        companion object {
            const val GRAVITY_TOP = 0
            const val GRAVITY_MIDDLE = 1
            const val GRAVITY_BOTTOM = 2
        }
    }
}