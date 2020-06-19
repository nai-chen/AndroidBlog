package com.blog.demo.md.recyclerview;

import android.content.Context;import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class CustomRefreshViewCreator extends RefreshViewCreator {

    private View mRefreshView;
    private ImageView mIvArrow;
    private ProgressBar mProgressBar;
    private TextView mTvRefreshTips;

    private String mPullToRefreshTips, mReleaseToRefreshTips, mRefreshingTips;
    private int mContentHeight;

    private int mHeadState = DONE;
    private int mDistance = 0;

    public CustomRefreshViewCreator(IOnRefreshListener listener) {
        super(listener);

        mPullToRefreshTips = "下拉刷新";
        mReleaseToRefreshTips = "松开刷新";
        mRefreshingTips = "正在刷新...";
    }

    @Override
    public View onCreateRefreshView(Context context, ViewGroup parent) {
        if (mRefreshView == null) {
            mRefreshView = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);

            mContentHeight = context.getResources().getDimensionPixelOffset(R.dimen.margin_dpi_50);
            mIvArrow = mRefreshView.findViewById(R.id.iv_arrow);
            mProgressBar = mRefreshView.findViewById(R.id.progress_bar_refresh);
            mTvRefreshTips = mRefreshView.findViewById(R.id.tv_refresh_tips);

            refreshFinish();
        }
        return mRefreshView;
    }

    protected @LayoutRes int getLayoutId() {
        return R.layout.list_view_refresh_view;
    }

    @Override
    public int move(int dy) {
        if (mHeadState != REFRESHING) {
            mDistance += dy;
            LogTool.logi("CustomRefreshViewCreator", "distance = " + mDistance);
            if (mDistance < 0) {
                mDistance = 0;
            } else if (mDistance > mContentHeight + 10) {
                mDistance = mContentHeight + 10;
            }

            if (mDistance <= 0) {
                mHeadState = DONE;
            } else if (mDistance >= mContentHeight) {
                mHeadState = RELEASE_TO_REFRESH;
            } else {
                mHeadState = PULL_TO_REFRESH;
            }
            changeHeaderViewByState();

            if (mHeadState == PULL_TO_REFRESH || mHeadState == RELEASE_TO_REFRESH) {
                int padding = mDistance - mContentHeight;
                mRefreshView.setPadding(0, padding > 0 ? 0 : padding, 0, 0);

                if (mDistance >= mContentHeight){
                    mIvArrow.setRotation(180);
                } else {
                    mIvArrow.setRotation(0);
                }
            }
        }
        return mDistance;
    }

    @Override
    public void release(float dy) {
        if (mHeadState != REFRESHING) {
            mDistance += dy;
            if (mDistance >= mContentHeight) {
                mHeadState = REFRESHING;
            } else {
                mHeadState = DONE;
            }
            mDistance = 0;
            changeHeaderViewByState();

            if (mHeadState == REFRESHING) {
                startRefresh();
            }
        }
    }

    @Override
    public void refreshFinish() {
        mHeadState = DONE;
        changeHeaderViewByState();
    }

    private void changeHeaderViewByState() {
        LogTool.logi("CustomRefreshViewCreator", "mHeadState = " + mHeadState);
        switch (mHeadState) {
            case DONE:
                mRefreshView.setPadding(0, -1 * mContentHeight + 1, 0, 0);

                mIvArrow.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);

                mTvRefreshTips.setText(mPullToRefreshTips);
                break;
            case PULL_TO_REFRESH:
                mIvArrow.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);

                mTvRefreshTips.setText(mPullToRefreshTips);
                break;
            case RELEASE_TO_REFRESH:
                mIvArrow.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);

                mTvRefreshTips.setText(mReleaseToRefreshTips);
                break;
            case REFRESHING:
                mRefreshView.setPadding(0, 0, 0, 0);

                mIvArrow.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.VISIBLE);

                mTvRefreshTips.setText(mRefreshingTips);
                break;
        }
    }

}
