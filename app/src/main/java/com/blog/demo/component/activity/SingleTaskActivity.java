package com.blog.demo.component.activity;

import android.view.View;

public class SingleTaskActivity extends StandardActivity implements View.OnClickListener {
	private final static String LOG_TAG = "SingleTaskActivity";

	@Override
	protected String getLogTag() {
		return LOG_TAG;
	}
}
