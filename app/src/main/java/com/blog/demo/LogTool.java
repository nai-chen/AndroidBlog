package com.blog.demo;

import android.util.Log;

public class LogTool {

    public static void logi(String tag, String message) {
        Log.i("com.blog.demo/" + tag, message);
    }

    public static void loge(String tag, Throwable throwable) {
        Log.e("com.blog.demo/" + tag, "", throwable);
    }

}
