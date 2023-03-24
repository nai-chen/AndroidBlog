package com.blog.demo.control.widget

import android.content.Context
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.blog.demo.LogTool.logi
import com.blog.demo.R

class CListViewHeaderView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        LinearLayout(context, attrs, defStyleAttr) {

    companion object {
        private val DONE                = 0
        private val PULL_TO_REFRESH     = 1 // 下拉刷新
        private val RELEASE_TO_REFRESH  = 2 // 放手刷新
        private val REFRESHING          = 3 // 刷新

        private val RATIO = 3
    }


    private var mIvArrow: ImageView
    private var mProgressBarRefresh: ProgressBar
    private var mTvRefreshTips: TextView

    private var mAnimCycle: Animation
    private var mAnimReverse:Animation? = null

    private var mPullToRefresh: String
    private var mReleaseToRefresh: String
    private var mRefreshing: String

    private var mContentHeight = 0

    private var mHeadState = DONE
    private var mReverse = false // 图标是否旋转

    private var mListView: CListView? = null
    private var mListener: CListView.IOnRefreshListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        inflate(context, R.layout.list_view_header_view, this)

        mIvArrow = findViewById(R.id.iv_arrow)
        mProgressBarRefresh = findViewById(R.id.progress_bar_refresh)
        mTvRefreshTips = findViewById(R.id.tv_refresh_tips)

        mAnimCycle = AnimationUtils.loadAnimation(context, R.anim.anim_list_view_rotate)
        mAnimReverse = AnimationUtils.loadAnimation(context, R.anim.anim_list_view_reverse)

        mContentHeight = context.resources.getDimensionPixelOffset(R.dimen.head_view_height)

        mPullToRefresh = context.getString(R.string.list_view_pull_to_refresh)
        mReleaseToRefresh = context.getString(R.string.list_view_release_to_refresh)
        mRefreshing = context.getString(R.string.list_view_refreshing)
    }

    fun setListView(listView: CListView) {
        mListView = listView
    }

    fun move(distance: Int) {
        logi("CListViewHeadView", "move distance = $distance, state = $mHeadState")
        if (mHeadState != REFRESHING) {
            if (mHeadState == DONE) {
                if (distance > 0) {
                    mHeadState = PULL_TO_REFRESH
                    changeHeaderViewByState()
                }
            }
            if (mHeadState == PULL_TO_REFRESH) {
                if (distance >= RATIO * mContentHeight) {
                    mHeadState = RELEASE_TO_REFRESH
                    mReverse = true
                    changeHeaderViewByState()
                } else if (distance <= 0) {
                    mHeadState = DONE
                    changeHeaderViewByState()
                }
            }
            if (mHeadState == RELEASE_TO_REFRESH) {
                if (distance <= 0) {
                    mHeadState = DONE
                    changeHeaderViewByState()
                } else if (distance < RATIO * mContentHeight) {
                    mHeadState = PULL_TO_REFRESH
                    changeHeaderViewByState()
                }
            }
            if (mHeadState == PULL_TO_REFRESH || mHeadState == RELEASE_TO_REFRESH) {
                mListView?.setSelection(0)
                setPadding(0, distance / RATIO - mContentHeight, 0, 0)
            }
        }
    }

    fun release(distance: Float) {
        logi("CListViewHeadView", "release distance = $distance, state = $mHeadState")
        if (distance >= RATIO * mContentHeight) {
            mHeadState = REFRESHING
        } else {
            mHeadState = DONE
        }
        changeHeaderViewByState()
        if (mHeadState == REFRESHING) {
            mListener?.onRefreshStart()
        }
    }

    fun setOnRefreshListener(listener: CListView.IOnRefreshListener?) {
        mListener = listener
    }

    fun refreshFinish() {
        mHeadState = DONE
        changeHeaderViewByState()
    }

    private fun changeHeaderViewByState() {
        when (mHeadState) {
            DONE -> {
                setPadding(0, -1 * mContentHeight, 0, 0)
                mIvArrow.clearAnimation()
                mProgressBarRefresh.visibility = GONE
                mTvRefreshTips.text = mPullToRefresh
            }
            PULL_TO_REFRESH -> {
                mIvArrow.visibility = VISIBLE
                mIvArrow.clearAnimation()
                mProgressBarRefresh.visibility = GONE
                mTvRefreshTips.text = mPullToRefresh

                // 是由RELEASE_To_REFRESH状态转变来的
                if (mReverse) {
                    mReverse = false
                    mIvArrow.startAnimation(mAnimReverse)
                }
            }
            RELEASE_TO_REFRESH -> {
                mIvArrow.visibility = VISIBLE
                mIvArrow.clearAnimation()
                mIvArrow.startAnimation(mAnimCycle)

                mProgressBarRefresh.visibility = GONE
                mTvRefreshTips.text = mReleaseToRefresh
            }
            REFRESHING -> {
                setPadding(0, 0, 0, 0)
                mIvArrow.clearAnimation()
                mIvArrow.visibility = GONE

                mProgressBarRefresh.visibility = VISIBLE
                mTvRefreshTips.text = mRefreshing
            }
        }
    }

}