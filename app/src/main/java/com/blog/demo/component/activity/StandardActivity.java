package com.blog.demo.component.activity;

import com.blog.demo.R;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class StandardActivity extends AbstractLifeActivity implements View.OnClickListener {
	private final static String LOG_TAG = "StandardActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_component_launch_mode);
		
		findViewById(R.id.btn_standard).setOnClickListener(this);
		findViewById(R.id.btn_single_top).setOnClickListener(this);
		findViewById(R.id.btn_single_task).setOnClickListener(this);
		findViewById(R.id.btn_single_instance).setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_standard:
			startActivity(new Intent(this, StandardActivity.class));
			break;
		case R.id.btn_single_top:
			startActivity(new Intent(this, SingleTopActivity.class));
			break;
		case R.id.btn_single_task:
			startActivity(new Intent(this, SingleTaskActivity.class));
			break;
		case R.id.btn_single_instance:
			startActivity(new Intent(this, SingleInstanceActivity.class));
			break;
		}
	}
	
	@Override
	protected String getLogTag() {
		return LOG_TAG;
	}

}
