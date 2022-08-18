package com.blog.demo.md.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blog.demo.LogTool;

public class CustomRecyclerView extends RecyclerView {
    private LinearLayoutManager mLayoutManager;
    private RefreshViewCreator mRefreshViewCreator;
    private LoadViewCreator mLoadViewCreator;
    private boolean mRecord;
    private int mLastEventY;
    private boolean mRefresh;
    private boolean mLoad;
    private int mTouchSlop;

    public CustomRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        setLayoutManager(mLayoutManager);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        LogTool.logi("CustomRefreshViewCreator", "mTouchSlop = " + mTouchSlop);
    }

    public void setRefreshViewCreator(RefreshViewCreator refreshViewCreator) {
        this.mRefreshViewCreator = refreshViewCreator;
    }

    public void setLoadViewCreator(LoadViewCreator loadViewCreator) {
        this.mLoadViewCreator = loadViewCreator;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                record(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                record(ev);

                int dy = (int) (ev.getY() - mLastEventY);
                mLastEventY = (int) ev.getY();
                if (allowRefresh()) {
                    if (dy > mTouchSlop) {
                        mRefresh = true;
                    }
                    if (mRefresh) {
                        int distance = mRefreshViewCreator.move(dy / 3);
                        if (distance == 0) {
                            ev.setAction(MotionEvent.ACTION_CANCEL);
                            return dispatchTouchEvent(ev);
                        }
                        return true;
                    }
                }
                if (allowLoad()) {
                    if (-dy > mTouchSlop) {
                        mLoad = true;
                    }
                    if (mLoad) {
                        int distance = mLoadViewCreator.move(-dy);
                        if (distance == 0) {
                            ev.setAction(MotionEvent.ACTION_CANCEL);
                            return dispatchTouchEvent(ev);
                        }
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mRecord = false;
                if (mRefresh) {
                    mRefreshViewCreator.release((int) (ev.getY() - mLastEventY));
                    mRefresh = false;
                    mLastEventY = 0;
                    return true;
                }
                if (mLoad) {
                    mLoadViewCreator.release((int) (mLastEventY - ev.getY()));
                    mLoad = false;
                    mLastEventY = 0;
                    return true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private void record(MotionEvent ev) {
        if ((allowRefresh() || allowLoad()) && !mRecord) {
            mLastEventY = (int) ev.getY();
            mRecord = true;
        }
    }

    private boolean allowRefresh() {
        return mRefreshViewCreator != null && !canScrollVertically(-1);
    }

    private boolean allowLoad() {
        return mLoadViewCreator != null && !canScrollVertically(1);
    }

}
