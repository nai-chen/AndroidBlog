package com.blog.demo.md.recyclerview

import android.content.Context
import android.view.View
import android.view.ViewGroup

abstract class RefreshViewCreator(listener: IOnRefreshListener?) {

    companion object {
        val DONE = 0
        val PULL_TO_REFRESH = 1 // 下拉刷新

        val RELEASE_TO_REFRESH = 2 // 释放刷新

        val REFRESHING = 3 // 刷新
    }

    var mListener: IOnRefreshListener? = listener

    abstract fun onCreateRefreshView(context: Context, parent: ViewGroup?): View?

    open fun move(dy: Int): Int {
        return 0
    }

    open fun release(dy: Float) {}

    open fun refreshFinish() {}

    protected open fun startRefresh() {
        mListener?.onRefresh()
    }

    interface IOnRefreshListener {
        fun onRefresh()
    }

}