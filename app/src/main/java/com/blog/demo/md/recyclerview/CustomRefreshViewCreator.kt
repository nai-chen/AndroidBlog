package com.blog.demo.md.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.blog.demo.LogTool.logi
import com.blog.demo.R

class CustomRefreshViewCreator(listener: IOnRefreshListener) : RefreshViewCreator(listener) {

    private var mRefreshView: View? = null
    private var mIvArrow: ImageView? = null
    private var mProgressBar: ProgressBar? = null
    private var mTvRefreshTips: TextView? = null

    private var mPullToRefreshTips: String = "下拉刷新"
    private var mReleaseToRefreshTips: String = "松开刷新"
    private var mRefreshingTips: String = "正在刷新..."
    private var mContentHeight = 0

    private var mHeadState = DONE
    private var mDistance = 0

    override fun onCreateRefreshView(context: Context, parent: ViewGroup?): View? {
        if (mRefreshView == null) {
            mRefreshView = LayoutInflater.from(context).inflate(getLayoutId(), parent, false)
            mContentHeight = context.resources.getDimensionPixelOffset(R.dimen.margin_dpi_50)
            mIvArrow = mRefreshView?.findViewById(R.id.iv_arrow)
            mProgressBar = mRefreshView?.findViewById(R.id.progress_bar_refresh)
            mTvRefreshTips = mRefreshView?.findViewById(R.id.tv_refresh_tips)
            refreshFinish()
        }
        return mRefreshView
    }

    @LayoutRes open fun getLayoutId(): Int {
        return R.layout.list_view_refresh_view
    }

    override fun move(dy: Int): Int {
        if (mHeadState != REFRESHING) {
            mDistance += dy
            logi("CustomRefreshViewCreator", "distance = $mDistance")
            if (mDistance < 0) {
                mDistance = 0
            } else if (mDistance > mContentHeight + 10) {
                mDistance = mContentHeight + 10
            }
            if (mDistance <= 0) {
                mHeadState = DONE
            } else if (mDistance >= mContentHeight) {
                mHeadState = RELEASE_TO_REFRESH
                mIvArrow?.rotation = 180f
            } else {
                mHeadState = PULL_TO_REFRESH
                mIvArrow?.rotation = 0f
            }
            if (mHeadState == PULL_TO_REFRESH || mHeadState == RELEASE_TO_REFRESH) {
                val padding = mDistance - mContentHeight
                mRefreshView?.setPadding(0, if (padding > 0) 0 else padding, 0, 0)
            }
            changeHeaderViewByState()
        }
        return mDistance
    }

    override fun release(dy: Float) {
        if (mHeadState != REFRESHING) {
            mDistance += dy.toInt()
            mHeadState = if (mDistance >= mContentHeight) {
                REFRESHING
            } else {
                DONE
            }
            mDistance = 0
            changeHeaderViewByState()
            if (mHeadState == REFRESHING) {
                startRefresh()
            }
        }
    }

    override fun refreshFinish() {
        mHeadState = DONE
        changeHeaderViewByState()
    }

    private fun changeHeaderViewByState() {
        logi("CustomRefreshViewCreator", "mHeadState = $mHeadState")
        when (mHeadState) {
            DONE -> {
                mRefreshView?.setPadding(0, -1 * mContentHeight, 0, 0)
                mIvArrow?.visibility = View.VISIBLE
                mProgressBar?.visibility = View.GONE
                mTvRefreshTips?.text = mPullToRefreshTips
            }
            PULL_TO_REFRESH -> {
                mIvArrow?.visibility = View.VISIBLE
                mProgressBar?.visibility = View.GONE
                mTvRefreshTips?.text = mPullToRefreshTips
            }
            RELEASE_TO_REFRESH -> {
                mIvArrow?.visibility = View.VISIBLE
                mProgressBar?.visibility = View.GONE
                mTvRefreshTips?.text = mReleaseToRefreshTips
            }
            REFRESHING -> {
                mRefreshView?.setPadding(0, 0, 0, 0)
                mIvArrow?.visibility = View.GONE
                mProgressBar?.visibility = View.VISIBLE
                mTvRefreshTips?.text = mRefreshingTips
            }
        }
    }

}