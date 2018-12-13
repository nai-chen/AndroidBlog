package com.blog.demo.component;

import com.blog.demo.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class IntentFlagFirstActivity extends AbstractLifeActivity implements View.OnClickListener {
	private final static String LOGTAG = "IntentFlagFirstActivity";
	
	private RadioGroup mRadioGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_flag);
		
		mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
		
		findViewById(R.id.btn_standard).setOnClickListener(this);
		findViewById(R.id.btn_new_task).setOnClickListener(this);
		findViewById(R.id.btn_single_top).setOnClickListener(this);
		findViewById(R.id.btn_clear_top).setOnClickListener(this);
		findViewById(R.id.btn_multi).setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = null;
		if (mRadioGroup.getCheckedRadioButtonId() == R.id.btn_first) {
			intent = new Intent(this, IntentFlagFirstActivity.class);
		} else if (mRadioGroup.getCheckedRadioButtonId() == R.id.btn_second) {
			intent = new Intent(this, IntentFlagSecondActivity.class);
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
		return LOGTAG;
	}

}
