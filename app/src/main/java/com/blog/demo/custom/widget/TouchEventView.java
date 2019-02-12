package com.blog.demo.custom.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.blog.demo.LogTool;

public class TouchEventView extends View {
    public final static String LOG_TAG = "TouchEventView";

    public TouchEventView(Context context) {
        super(context);
    }

    public TouchEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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

//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            return true;
//        } else {
//            return false;
//        }
    }

}
