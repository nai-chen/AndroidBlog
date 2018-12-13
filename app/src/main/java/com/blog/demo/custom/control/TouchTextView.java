package com.blog.demo.custom.control;

import com.blog.demo.LogUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

public class TouchTextView extends TextView {
	private final static String LOGTAG = "TouchTextView";
	
	public TouchTextView(Context context) {
		super(context);
	}

	public TouchTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		LogUtil.log(LOGTAG, "before dispatchTouchEvent " + event.getAction());
		boolean handle = super.dispatchTouchEvent(event);
		LogUtil.log(LOGTAG, "after dispatchTouchEvent");
		return handle;
//		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			return true;
//		} else {
//			return false;
//		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		LogUtil.log(LOGTAG, "before onTouchEvent " + event.getAction());
		boolean handle = super.onTouchEvent(event);
		LogUtil.log(LOGTAG, "after onTouchEvent");
//		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			return true;
//		} else {
//			return false;
//		}
		return handle;
	}
	
}
