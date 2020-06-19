package com.blog.demo.md.recyclerview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class CustomLoadViewCreator extends LoadViewCreator {

    private View mLoadView;
    private ProgressBar mProgressBar;
    private TextView mTvLoadTips;

    private String mPullToLoadTips, mReleaseToLoadTips, mLoadingTips;
    private int mContentHeight;

    private int mHeadState = DONE;
    private int mDistance = 0;

    public CustomLoadViewCreator(IOnLoadListener listener) {
        super(listener);

        mPullToLoadTips = "上拉加载更多";
        mReleaseToLoadTips = "松开加载更多";
        mLoadingTips = "正在加载...";
    }

    @Override
    public View onCreateLoadView(Context context, ViewGroup parent) {
        if (mLoadView == null) {
            mLoadView = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);

            mContentHeight = context.getResources().getDimensionPixelOffset(R.dimen.margin_dpi_45);
            mProgressBar = mLoadView.findViewById(R.id.progress_bar_load);
            mTvLoadTips = mLoadView.findViewById(R.id.tv_load_tips);

            loadFinish();
            LogTool.logi("CustomLoadViewCreator", "mContentHeight = " + mContentHeight);
        }
        return mLoadView;
    }

    protected @LayoutRes int getLayoutId() {
        return R.layout.list_view_load_view;
    }

    @Override
    public int move(int dy) {
        if (mHeadState != LOADING) {
            mDistance += dy;
            LogTool.logi("CustomLoadViewCreator", "distance = " + mDistance);
            if (mDistance < 0) {
                mDistance = 0;
            } else if (mDistance > mContentHeight + 10) {
                mDistance = mContentHeight + 10;
            }

            if (mDistance <= 0) {
                mHeadState = DONE;
            } else if (mDistance >= mContentHeight) {
                mHeadState = RELEASE_TO_LOAD;

            } else {
                mHeadState = PULL_TO_LOAD;
            }

            if (mHeadState == PULL_TO_LOAD || mHeadState == RELEASE_TO_LOAD) {
                int padding = mDistance - mContentHeight;
                mLoadView.setPadding(0, 0, 0, padding > 0 ? 0 : padding);
            }

            changeHeaderViewByState();
        }
        return mDistance;
    }

    @Override
    public void release(float dy) {
        if (mHeadState != LOADING) {
            mDistance += dy;
            LogTool.logi("CustomLoadViewCreator", "release = " + mDistance);
            if (mDistance >= mContentHeight) {
                mHeadState = LOADING;
            } else {
                mHeadState = DONE;
            }
            mDistance = 0;
            changeHeaderViewByState();

            if (mHeadState == LOADING) {
                startLoad();
            }
        }
    }

    @Override
    public void loadFinish() {
        mHeadState = DONE;
        changeHeaderViewByState();
    }

    private void changeHeaderViewByState() {
        LogTool.logi("CustomLoadViewCreator", "mHeadState = " + mHeadState);
        switch (mHeadState) {
            case DONE:
                mLoadView.setPadding(0, 0, 0, -1 * mContentHeight + 1);

                mProgressBar.setVisibility(View.GONE);

                mTvLoadTips.setText(mPullToLoadTips);
                break;
            case PULL_TO_LOAD:
                mProgressBar.setVisibility(View.GONE);

                mTvLoadTips.setText(mPullToLoadTips);
                break;
            case RELEASE_TO_LOAD:
                mProgressBar.setVisibility(View.GONE);

                mTvLoadTips.setText(mReleaseToLoadTips);
                break;
            case LOADING:
                mLoadView.setPadding(0, 0, 0, 0);

                mProgressBar.setVisibility(View.VISIBLE);

                mTvLoadTips.setText(mLoadingTips);
                break;
        }
    }

}
