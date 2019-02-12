package com.blog.demo.custom;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import com.blog.demo.LogTool;
import com.blog.demo.R;

public class TouchEventActivity extends Activity {
    private final static String LOG_TAG = "TouchEventActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_touch_event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogTool.logi(LOG_TAG, "before dispatchTouchEvent event = " + event.getAction());
        boolean handle = super.dispatchTouchEvent(event);
        LogTool.logi(LOG_TAG, "after dispatchTouchEvent handle = " + handle);
        return handle;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogTool.logi(LOG_TAG, "before onTouchEvent event = " + event.getAction());
        boolean handle = super.onTouchEvent(event);
        LogTool.logi(LOG_TAG, "after onTouchEvent handle = " + handle);
        return handle;
    }

}
