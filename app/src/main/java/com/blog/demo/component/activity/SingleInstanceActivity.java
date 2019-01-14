package com.blog.demo.component.activity;

import android.view.View;

public class SingleInstanceActivity extends StandardActivity implements View.OnClickListener {
	private final static String LOG_TAG = "SingleInstanceActivity";
	
	@Override
	protected String getLogTag() {
		return LOG_TAG;
	}
}
