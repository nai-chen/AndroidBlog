package com.blog.demo.component;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import android.os.Bundle;
import android.view.View;

public class LifeActivity extends AbstractLifeActivity {
	private final static String LOGTAG = "LifeActivity";
	
	private int iValue = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LogUtil.log(LOGTAG, "i = " + iValue);
		
		setContentView(R.layout.activity_life);
		findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				iValue += 10;
				LogUtil.log(LOGTAG, "i = " + iValue);
			}
		});
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		LogUtil.log(LOGTAG, "onSaveInstanceState");
		
		outState.putInt("key1", 23);
		outState.putInt("key2", iValue);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		LogUtil.log(LOGTAG, "onRestoreInstanceState");
		
		LogUtil.log(LOGTAG, "key1 = " + savedInstanceState.getInt("key1"));
		LogUtil.log(LOGTAG, "key2 = " + savedInstanceState.getInt("key2"));
	}
	
	@Override
	protected String getLogTag() {
		return LOGTAG;
	}
	
}
