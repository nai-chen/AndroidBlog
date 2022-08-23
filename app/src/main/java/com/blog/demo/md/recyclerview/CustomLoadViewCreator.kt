package com.blog.demo.md.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.blog.demo.LogTool.logi
import com.blog.demo.R

class CustomLoadViewCreator(listener: IOnLoadListener?) : LoadViewCreator(listener) {

    private var mLoadView: View? = null
    private var mProgressBar: ProgressBar? = null
    private var mTvLoadTips: TextView? = null

    private var mPullToLoadTips: String = "上拉加载更多"
    private var mReleaseToLoadTips: String = "松开加载更多"
    private var mLoadingTips: String = "正在加载..."
    private var mContentHeight = 0

    private var mHeadState = DONE
    private var mDistance = 0

    override fun onCreateLoadView(context: Context, parent: ViewGroup): View? {
        if (mLoadView == null) {
            mLoadView = LayoutInflater.from(context).inflate(getLayoutId(), parent, false)
            mContentHeight = context.resources.getDimensionPixelOffset(R.dimen.margin_dpi_45)
            mProgressBar = mLoadView?.findViewById(R.id.progress_bar_load)
            mTvLoadTips = mLoadView?.findViewById(R.id.tv_load_tips)
            loadFinish()
            logi("CustomLoadViewCreator", "mContentHeight = $mContentHeight")
        }
        return mLoadView
    }

    @LayoutRes open fun getLayoutId(): Int {
        return R.layout.list_view_load_view
    }

    override fun move(dy: Int): Int {
        if (mHeadState != LOADING) {
            mDistance += dy
            logi("CustomLoadViewCreator", "distance = $mDistance")
            if (mDistance < 0) {
                mDistance = 0
            } else if (mDistance > mContentHeight + 10) {
                mDistance = mContentHeight + 10
            }
            mHeadState = if (mDistance <= 0) {
                DONE
            } else if (mDistance >= mContentHeight) {
                RELEASE_TO_LOAD
            } else {
                PULL_TO_LOAD
            }
            if (mHeadState == PULL_TO_LOAD || mHeadState == RELEASE_TO_LOAD) {
                val padding = mDistance - mContentHeight
                mLoadView?.setPadding(0, 0, 0, if (padding > 0) 0 else padding)
            }
            changeHeaderViewByState()
        }
        return mDistance
    }

    override fun release(dy: Float) {
        if (mHeadState != LOADING) {
            mDistance += dy.toInt()
            logi("CustomLoadViewCreator", "release = $mDistance")
            mHeadState = if (mDistance >= mContentHeight) {
                LOADING
            } else {
                DONE
            }
            mDistance = 0
            changeHeaderViewByState()
            if (mHeadState == LOADING) {
                startLoad()
            }
        }
    }

    override fun loadFinish() {
        mHeadState = DONE
        changeHeaderViewByState()
    }

    private fun changeHeaderViewByState() {
        logi("CustomLoadViewCreator", "mHeadState = $mHeadState")
        when (mHeadState) {
            DONE -> {
                mLoadView?.setPadding(0, 0, 0, -1 * mContentHeight + 1)
                mProgressBar?.visibility = View.GONE
                mTvLoadTips?.text = mPullToLoadTips
            }
            PULL_TO_LOAD -> {
                mProgressBar?.visibility = View.GONE
                mTvLoadTips?.text = mPullToLoadTips
            }
            RELEASE_TO_LOAD -> {
                mProgressBar?.visibility = View.GONE
                mTvLoadTips?.text = mReleaseToLoadTips
            }
            LOADING -> {
                mLoadView?.setPadding(0, 0, 0, 0)
                mProgressBar?.visibility = View.VISIBLE
                mTvLoadTips?.text = mLoadingTips
            }
        }
    }

}