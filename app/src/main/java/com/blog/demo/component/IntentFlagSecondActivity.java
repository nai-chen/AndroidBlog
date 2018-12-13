package com.blog.demo.component;

import android.view.View;

public class IntentFlagSecondActivity extends IntentFlagFirstActivity implements View.OnClickListener {
	private final static String LOGTAG = "IntentFlagSecondActivity";
	
	@Override
	protected String getLogTag() {
		return LOGTAG;
	}

}
