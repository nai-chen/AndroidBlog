package com.blog.demo.component;

import android.view.View;

public class SingleTaskActivity extends StandardActivity implements View.OnClickListener {
	private final static String LOGTAG = "SingleTaskActivity";

	@Override
	protected String getLogTag() {
		return LOGTAG;
	}
}
