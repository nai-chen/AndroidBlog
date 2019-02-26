package com.blog.demo.custom.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.AbsListView;

import com.blog.demo.LogTool;

public class CListView extends ListView implements AbsListView.OnScrollListener {
    private CListViewHeaderView mHeaderView;
    private int mFirstItemIndex;

    private float mStartY, mCurrentY;
    private boolean mRefreshEnable, mRecord;

    public CListView(Context context) {
        this(context, null);
    }

    public CListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHeaderView = new CListViewHeaderView(context);
        mHeaderView.setListView(this);
        addHeaderView(mHeaderView);
        mHeaderView.refreshFinish();

        setOnScrollListener(this);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mFirstItemIndex = firstVisibleItem;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 保证触摸的y值只记录一次
                if (!mRecord) {
                    mStartY = event.getY();
                    mRecord = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mRecord) {
                    mStartY = event.getY();
                    mRecord = true;
                }
                mCurrentY = event.getY();
                LogTool.logi("CListView", "MOVE startY = " + mStartY + ", currentY = " + mCurrentY);
                if (mRefreshEnable && mFirstItemIndex == 0) {
                    mHeaderView.move((int) (mCurrentY - mStartY));
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mCurrentY = event.getY();
                LogTool.logi("CListView", "UP startY = " + mStartY + ", currentY = " + mCurrentY);
                if (mRefreshEnable && mFirstItemIndex == 0 && mRecord) {
                    mHeaderView.release((int) (mCurrentY - mStartY));
                }

                mRecord = false;
                break;
        }
        return super.onTouchEvent(event);
    }

    /*
     * 刷新回调
     */
    public void refreshFinish() {
        mHeaderView.refreshFinish();
    }

    public void setOnRefreshListener(IOnRefreshListener refreshListener) {
        mRefreshEnable = (refreshListener != null);

        mHeaderView.setOnRefreshListener(refreshListener);
    }

    public interface IOnRefreshListener {
        void onRefreshStart();
    }

}
