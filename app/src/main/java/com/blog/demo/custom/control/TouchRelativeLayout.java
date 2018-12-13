package com.blog.demo.custom.control;

import com.blog.demo.LogUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class TouchRelativeLayout extends RelativeLayout {
	private final static String LOGTAG = "TouchRelativeLayout";
	
	public TouchRelativeLayout(Context context) {
		super(context);
	}

	public TouchRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		LogUtil.log(LOGTAG, "before dispatchTouchEvent " + event.getAction());
		boolean handle = super.dispatchTouchEvent(event);
		LogUtil.log(LOGTAG, "after dispatchTouchEvent");
		return handle;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		LogUtil.log(LOGTAG, "before onInterceptTouchEvent " + ev.getAction());
		boolean handle = super.onInterceptTouchEvent(ev);
		LogUtil.log(LOGTAG, "after onInterceptTouchEvent");
		return handle;
//		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//			return false;
//		} else {
//			return true;
//		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		LogUtil.log(LOGTAG, "before onTouchEvent " + event.getAction());
		boolean handle = super.onTouchEvent(event);
		LogUtil.log(LOGTAG, "after onTouchEvent");
		return handle;
//		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			return true;
//		} else {
//			return false;
//		}
	}

}
