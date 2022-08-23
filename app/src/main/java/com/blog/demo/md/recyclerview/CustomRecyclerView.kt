package com.blog.demo.md.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blog.demo.LogTool.logi

class CustomRecyclerView(context: Context, attrs: AttributeSet?, defStyle: Int) :
    RecyclerView(context, attrs, defStyle) {
    private val mLayoutManager: LinearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    private var mRefreshViewCreator: RefreshViewCreator? = null
    private var mLoadViewCreator: LoadViewCreator? = null

    private var mRecord = false
    private var mLastEventY = 0
    private var mRefresh = false
    private var mLoad = false

    private var mTouchSlop = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        layoutManager = mLayoutManager

        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
        logi("CustomRefreshViewCreator", "mTouchSlop = $mTouchSlop")
    }

    fun setRefreshViewCreator(refreshViewCreator: RefreshViewCreator?) {
        this.mRefreshViewCreator = refreshViewCreator
    }

    fun setLoadViewCreator(loadViewCreator: LoadViewCreator?) {
        this.mLoadViewCreator = loadViewCreator
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> record(ev)
            MotionEvent.ACTION_MOVE -> {
                record(ev)
                val dy: Int = (ev.y - mLastEventY).toInt()
                mLastEventY = ev.y.toInt()
                if (allowRefresh()) {
                    if (dy > mTouchSlop) {
                        mRefresh = true
                    }
                    if (mRefresh) {
                        val distance: Int = mRefreshViewCreator?.move(dy / 3) ?: 0
                        if (distance == 0) {
                            ev.action = MotionEvent.ACTION_CANCEL
                            return dispatchTouchEvent(ev)
                        }
                        return true
                    }
                }
                if (allowLoad()) {
                    if (-dy > mTouchSlop) {
                        mLoad = true
                    }
                    if (mLoad) {
                        val distance: Int = mLoadViewCreator?.move(-dy) ?: 0
                        if (distance == 0) {
                            ev.action = MotionEvent.ACTION_CANCEL
                            return dispatchTouchEvent(ev)
                        }
                        return true
                    }
                }
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                mRecord = false
                if (mRefresh) {
                    mRefreshViewCreator?.release((ev.y - mLastEventY).toInt().toFloat())
                    mRefresh = false
                    mLastEventY = 0
                    return true
                }
                if (mLoad) {
                    mLoadViewCreator?.release((mLastEventY - ev.y).toInt().toFloat())
                    mLoad = false
                    mLastEventY = 0
                    return true
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun record(ev: MotionEvent) {
        if ((allowRefresh() || allowLoad()) && !mRecord) {
            mLastEventY = ev.y.toInt()
            mRecord = true
        }
    }

    private fun allowRefresh(): Boolean {
        var firstPosition = mLayoutManager?.findFirstCompletelyVisibleItemPosition()
        return mRefreshViewCreator != null && (!canScrollVertically(-1) || firstPosition == 0 || firstPosition == 1)
    }

    private fun allowLoad(): Boolean {
        return mLoadViewCreator != null && !canScrollVertically(1)
    }

}