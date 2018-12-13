package com.blog.demo.component;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

public class LifeSecondActivity extends AbstractLifeActivity {
	private final static String LOGTAG = "LifeSecondActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_life);

		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LifeSecondActivity.this, LifeFirstActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
				intent.putExtra("start", true);

				LogUtil.log(getLogTag(), "111");
				startActivity(intent);
				LogUtil.log(getLogTag(), "222");
				setResult(111);
			}
		});
	}

	@Override
	protected String getLogTag() {
		return LOGTAG;
	}
}
