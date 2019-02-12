package com.blog.demo.custom.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.blog.demo.LogTool;

public class TouchEventViewGroup extends RelativeLayout {
    private final static String LOG_TAG = "TouchRelativeLayout";

    public TouchEventViewGroup(Context context) {
        super(context);
    }

    public TouchEventViewGroup(Context context, AttributeSet attrs) {
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
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogTool.logi(LOG_TAG, "before onInterceptTouchEvent event = " + ev.getAction());
        boolean handle = super.onInterceptTouchEvent(ev);
        LogTool.logi(LOG_TAG, "after onInterceptTouchEvent handle = " + handle);
        return handle;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogTool.logi(LOG_TAG, "before onTouchEvent event = " + event.getAction());
//        boolean handle = super.onTouchEvent(event);
//        LogTool.logi(LOG_TAG, "after onTouchEvent handle = " + handle);
//        return handle;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        } else {
            return false;
        }
    }

}
