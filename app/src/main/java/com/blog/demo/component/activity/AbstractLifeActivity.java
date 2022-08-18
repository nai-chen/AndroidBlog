package com.blog.demo.component.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.blog.demo.LogTool;

public class AbstractLifeActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogTool.logi(getLogTag(), "onCreate");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		LogTool.logi(getLogTag(), "onRestart");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		LogTool.logi(getLogTag(), "onStart");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		LogTool.logi(getLogTag(), "onResume");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		LogTool.logi(getLogTag(), "onPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		LogTool.logi(getLogTag(), "onStop");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		LogTool.logi(getLogTag(), "onDestroy");
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		LogTool.logi(getLogTag(), "onNewIntent");
	}
	
	protected String getLogTag() {
		return "AbstractLifeActivity";
	}
	
}
