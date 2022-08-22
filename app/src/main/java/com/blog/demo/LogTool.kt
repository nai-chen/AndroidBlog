package com.blog.demo

import android.util.Log

object LogTool {

    @JvmStatic
    fun logi(tag: String, message: String?) {
        Log.i("com.blog.demo/$tag", message ?: "")
    }

    @JvmStatic
    fun loge(tag: String, throwable: Throwable?) {
        Log.e("com.blog.demo/$tag", "", throwable)
    }

}