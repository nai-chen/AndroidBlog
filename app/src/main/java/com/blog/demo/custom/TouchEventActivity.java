package com.blog.demo.custom;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class TouchEventActivity extends Activity implements View.OnTouchListener {
	private final static String LOGTAG = "TouchEventActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_touch);
		
//		TouchRelativeLayout layout = (TouchRelativeLayout) findViewById(R.id.touch_rl);
//		layout.setOnTouchListener(this);
//
//		TouchTextView tv = (TouchTextView) findViewById(R.id.touch_tv);
//		tv.setOnTouchListener(this);
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		LogUtil.log(LOGTAG, "onTouch " + event.getAction());
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			switch(v.getId()) {
			case R.id.touch_rl:
				return true;
			case R.id.touch_tv:
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		LogUtil.log(LOGTAG, "before dispatchTouchEvent " + event.getAction());
		boolean handle = super.dispatchTouchEvent(event);
		LogUtil.log(LOGTAG, "after dispatchTouchEvent");
		return handle;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		LogUtil.log(LOGTAG, "before onTouchEvent " + event.getAction());
		boolean handle = super.onTouchEvent(event);
		LogUtil.log(LOGTAG, "after onTouchEvent");
		return true;
	}
	
}
