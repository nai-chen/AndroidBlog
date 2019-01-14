package com.blog.demo.component.activity;

import android.view.View;

public class IntentFlagOtherActivity extends IntentFlagActivity implements View.OnClickListener {
	private final static String LOG_TAG = "IntentFlagOtherActivity";
	
	@Override
	protected String getLogTag() {
		return LOG_TAG;
	}

}
