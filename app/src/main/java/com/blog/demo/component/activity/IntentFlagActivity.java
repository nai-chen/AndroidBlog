package com.blog.demo.component.activity;

import com.blog.demo.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class IntentFlagActivity extends AbstractLifeActivity implements View.OnClickListener {
	private final static String LOG_TAG = "IntentFlagFirstActivity";
	
	private RadioGroup mRadioGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_component_intent_flag);
		
		mRadioGroup = findViewById(R.id.radio_group);
		
		findViewById(R.id.btn_standard).setOnClickListener(this);
		findViewById(R.id.btn_new_task).setOnClickListener(this);
		findViewById(R.id.btn_single_top).setOnClickListener(this);
		findViewById(R.id.btn_clear_top).setOnClickListener(this);
		findViewById(R.id.btn_multi).setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = null;
		if (mRadioGroup.getCheckedRadioButtonId() == R.id.rb_intent_flag) {
			intent = new Intent(this, IntentFlagActivity.class);
		} else if (mRadioGroup.getCheckedRadioButtonId() == R.id.rb_intent_flag_other) {
			intent = new Intent(this, IntentFlagOtherActivity.class);
		}
		if (intent == null) return;
		
		switch (v.getId()) {
		case R.id.btn_standard:
			break;
		case R.id.btn_new_task:
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			break;
		case R.id.btn_single_top:
			intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			break;
		case R.id.btn_clear_top:
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			break;
		case R.id.btn_multi:
			intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
			break;
		}
		startActivity(intent);
	}

	@Override
	protected String getLogTag() {
		return LOG_TAG;
	}

}
