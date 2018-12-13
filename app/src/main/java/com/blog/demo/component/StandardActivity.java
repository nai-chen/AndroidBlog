package com.blog.demo.component;

import com.blog.demo.R;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class StandardActivity extends AbstractLifeActivity implements View.OnClickListener {
	private final static String LOGTAG = "StandardActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_launch_mode);
		
		findViewById(R.id.btn_standard).setOnClickListener(this);
		findViewById(R.id.btn_single_top).setOnClickListener(this);
		findViewById(R.id.btn_single_task).setOnClickListener(this);
		findViewById(R.id.btn_single_instance).setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.btn_standard:
			intent = new Intent(this, StandardActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_single_top:
			intent = new Intent(this, SingleTopActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_single_task:
			intent = new Intent(this, SingleTaskActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_single_instance:
			intent = new Intent(this, SingleInstanceActivity.class);
			startActivity(intent);
			break;
		}
	}
	
	@Override
	protected String getLogTag() {
		return LOGTAG;
	}

}
