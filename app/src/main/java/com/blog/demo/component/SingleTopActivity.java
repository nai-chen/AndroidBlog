package com.blog.demo.component;

import android.view.View;

public class SingleTopActivity extends StandardActivity implements View.OnClickListener {
	private final static String LOGTAG = "SingleTopActivity";
	
	@Override
	protected String getLogTag() {
		return LOGTAG;
	}
	
}
