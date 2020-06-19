package com.blog.demo.md.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public abstract class RefreshViewCreator {
    public final static int DONE               = 0;
    public final static int PULL_TO_REFRESH    = 1; // 下拉刷新
    public final static int RELEASE_TO_REFRESH = 2; // 释放刷新
    public final static int REFRESHING         = 3; // 刷新

    public IOnRefreshListener mListener;

    public RefreshViewCreator(IOnRefreshListener listener) {
        this.mListener = listener;
    }
    
    public abstract View onCreateRefreshView(Context context, ViewGroup parent);

    public int move(int dy) {
        return 0;
    }

    public void release(float dy) {
    }

    public void refreshFinish() {
    }
    
    protected void startRefresh() {
        if (mListener != null) {
            mListener.onRefresh();
        }
    }

    public interface IOnRefreshListener {
        void onRefresh();
    }
    
}
