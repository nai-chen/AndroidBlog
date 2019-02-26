package com.blog.demo.custom.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class CListViewHeaderView extends LinearLayout {
    private final static int DONE               = 0;
    private final static int PULL_TO_REFRESH    = 1; // 下拉刷新
    private final static int RELEASE_TO_REFRESH = 2; // 放手刷新
    private final static int REFRESHING         = 3; // 刷新

    private final static int RATIO              = 3;

    private ImageView mIvArrow;
    private ProgressBar mProgressBarRefresh;
    private TextView mTvRefreshTips;

    private Animation mAnimCycle, mAnimReverse;

    private String mPullToRefresh, mReleaseToRefresh, mRefreshing;
    private int mContentHeight;

    private int mHeadState = DONE;
    private boolean mReverse; // 图标是否旋转

    private CListView mListView;
    private CListView.IOnRefreshListener mListener;

    public CListViewHeaderView(Context context) {
        this(context, null);
    }

    public CListViewHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.list_view_header_view, this);

        mIvArrow = findViewById(R.id.iv_arrow);
        mProgressBarRefresh = findViewById(R.id.progress_bar_refresh);
        mTvRefreshTips = findViewById(R.id.tv_refresh_tips);

        mAnimCycle = AnimationUtils.loadAnimation(context, R.anim.anim_list_view_rotate);
        mAnimReverse = AnimationUtils.loadAnimation(context, R.anim.anim_list_view_reverse);

        mContentHeight = context.getResources().getDimensionPixelOffset(R.dimen.head_view_height);

        mPullToRefresh = context.getString(R.string.list_view_pull_to_refresh);
        mReleaseToRefresh = context.getString(R.string.list_view_release_to_refresh);
        mRefreshing = context.getString(R.string.list_view_refreshing);
    }

    void setListView(CListView listView) {
        this.mListView = listView;
    }

    void move(int distance) {
        LogTool.logi("CListViewHeadView", "move distance = " + distance + ", state = " + mHeadState);
        if (mHeadState != REFRESHING) {
            if (mHeadState == DONE) {
                if (distance > 0) {
                    mHeadState = PULL_TO_REFRESH;
                    changeHeaderViewByState();
                }
            }
            if (mHeadState == PULL_TO_REFRESH) {
                if (distance >= RATIO * mContentHeight) {
                    mHeadState = RELEASE_TO_REFRESH;
                    mReverse = true;
                    changeHeaderViewByState();
                } else if (distance <= 0) {
                    mHeadState = DONE;
                    changeHeaderViewByState();
                }
            }
            if (mHeadState == RELEASE_TO_REFRESH) {
                if (distance <= 0) {
                    mHeadState = DONE;
                    changeHeaderViewByState();
                } else if (distance < RATIO * mContentHeight) {
                    mHeadState = PULL_TO_REFRESH;
                    changeHeaderViewByState();
                }
            }
            if (mHeadState == PULL_TO_REFRESH || mHeadState == RELEASE_TO_REFRESH) {
                if (mListView != null) {
                    mListView.setSelection(0);
                }
                setPadding(0, distance / RATIO - mContentHeight, 0, 0);
            }
        }
    }

    void release(float distance) {
        LogTool.logi("CListViewHeadView", "release distance = " + distance + ", state = " + mHeadState);
        if (distance >= RATIO * mContentHeight) {
            mHeadState = REFRESHING;
        } else {
            mHeadState = DONE;
        }
        changeHeaderViewByState();

        if (mHeadState == REFRESHING) {
            mListener.onRefreshStart();
        }
    }

    void setOnRefreshListener(CListView.IOnRefreshListener listener) {
        this.mListener = listener;
    }

    public void refreshFinish() {
        mHeadState = DONE;
        changeHeaderViewByState();
    }

    private void changeHeaderViewByState() {
        switch (mHeadState) {
            case DONE:
                setPadding(0, -1 * mContentHeight, 0, 0);

                mIvArrow.clearAnimation();
                mProgressBarRefresh.setVisibility(View.GONE);
                mTvRefreshTips.setText(mPullToRefresh);
                break;
            case PULL_TO_REFRESH:
                mIvArrow.setVisibility(View.VISIBLE);
                mIvArrow.clearAnimation();

                mProgressBarRefresh.setVisibility(View.GONE);
                mTvRefreshTips.setText(mPullToRefresh);

                // 是由RELEASE_To_REFRESH状态转变来的
                if (mReverse) {
                    mReverse = false;
                    mIvArrow.startAnimation(mAnimReverse);
                }
                break;
            case RELEASE_TO_REFRESH:
                mIvArrow.setVisibility(View.VISIBLE);
                mIvArrow.clearAnimation();

                mIvArrow.startAnimation(mAnimCycle);

                mProgressBarRefresh.setVisibility(View.GONE);
                mTvRefreshTips.setText(mReleaseToRefresh);
                break;
            case REFRESHING:
                setPadding(0, 0, 0, 0);

                mIvArrow.clearAnimation();
                mIvArrow.setVisibility(View.GONE);

                mProgressBarRefresh.setVisibility(View.VISIBLE);
                mTvRefreshTips.setText(mRefreshing);
                break;
        }
    }

}
