package com.blog.demo.component;

import com.blog.demo.LogUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AbstractLifeActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.log(getLogTag(), "onCreate");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		LogUtil.log(getLogTag(), "onRestart");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		LogUtil.log(getLogTag(), "onStart");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		LogUtil.log(getLogTag(), "onResume");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		LogUtil.log(getLogTag(), "onPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		LogUtil.log(getLogTag(), "onStop");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		LogUtil.log(getLogTag(), "onDestroy");
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		LogUtil.log(getLogTag(), "onNewIntent");
	}
	
	protected String getLogTag() {
		return "AbstractLifeActivity";
	}
	
}
