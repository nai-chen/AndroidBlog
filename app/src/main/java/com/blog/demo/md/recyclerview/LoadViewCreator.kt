package com.blog.demo.md.recyclerview

import android.content.Context
import android.view.View
import android.view.ViewGroup

abstract class LoadViewCreator(listener: IOnLoadListener?) {

    companion object {
        val DONE = 0
        val PULL_TO_LOAD = 1 // 上拉加载

        val RELEASE_TO_LOAD = 2 // 释放加载

        val LOADING = 3 // 加载
    }

    private var mListener: IOnLoadListener? = listener

    abstract fun onCreateLoadView(context: Context, parent: ViewGroup): View?

    open fun move(dy: Int): Int {
        return 0
    }

    open fun release(dy: Float) {}

    open fun loadFinish() {}

    protected open fun startLoad() {
        mListener?.onLoad()
    }

    interface IOnLoadListener {
        fun onLoad()
    }

}