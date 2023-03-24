package com.blog.demo.control.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.AbsListView
import android.widget.ListView
import com.blog.demo.LogTool

class CListView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        ListView(context, attrs, defStyleAttr), AbsListView.OnScrollListener {

    private var mHeaderView: CListViewHeaderView
    private var mFirstItemIndex = 0

    private var mStartY = 0f
    private var mCurrentY = 0f
    private var mRefreshEnable = false
    private var mRecord = false

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        mHeaderView = CListViewHeaderView(context)
        mHeaderView.setListView(this)
        addHeaderView(mHeaderView)
        mHeaderView.refreshFinish()

        setOnScrollListener(this)
    }

    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        mFirstItemIndex = firstVisibleItem
    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {    // 保证触摸的y值只记录一次
                if (!mRecord) {
                    mStartY = event.y
                    mRecord = true
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (!mRecord) {
                    mStartY = event.y
                    mRecord = true
                }
                mCurrentY = event.y
                LogTool.logi("CListView", "MOVE startY = $mStartY, currentY = $mCurrentY")
                if (mRefreshEnable && mFirstItemIndex == 0) {
                    mHeaderView.move((mCurrentY - mStartY).toInt())
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                mCurrentY = event.y
                LogTool.logi("CListView", "UP startY = $mStartY, currentY = $mCurrentY")
                if (mRefreshEnable && mFirstItemIndex == 0 && mRecord) {
                    mHeaderView.release((mCurrentY - mStartY).toInt().toFloat())
                }
                mRecord = false
            }
        }
        return super.onTouchEvent(event)
    }

    /*
     * 刷新回调
     */
    fun refreshFinish() {
        mHeaderView.refreshFinish()
    }

    fun setOnRefreshListener(refreshListener: IOnRefreshListener) {
        mRefreshEnable = refreshListener != null
        mHeaderView.setOnRefreshListener(refreshListener)
    }

    interface IOnRefreshListener {
        fun onRefreshStart()
    }

}