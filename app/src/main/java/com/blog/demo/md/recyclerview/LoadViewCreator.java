package com.blog.demo.md.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public abstract class LoadViewCreator {
    public final static int DONE                = 0;
    public final static int PULL_TO_LOAD        = 1; // 上拉加载
    public final static int RELEASE_TO_LOAD     = 2; // 释放加载
    public final static int LOADING             = 3; // 加载

    public IOnLoadListener mListener;

    public LoadViewCreator(IOnLoadListener listener) {
        this.mListener = listener;
    }
    
    public abstract View onCreateLoadView(Context context, ViewGroup parent);

    public int move(int dy) {
        return 0;
    }

    public void release(float dy) {
    }

    public void loadFinish() {
    }
    
    protected void startLoad() {
        if (mListener != null) {
            mListener.onLoad();
        }
    }

    public interface IOnLoadListener {
        void onLoad();
    }
    
}
