package com.blog.demo.component;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LifeFirstActivity extends AbstractLifeActivity {
	private final static String LOGTAG = "LifeFirstActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_life);
		
		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LifeFirstActivity.this, LifeSecondActivity.class);
				startActivityForResult(intent, 1);
			}
		});
	}
	
	@Override
	protected String getLogTag() {
		return LOGTAG;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			LogUtil.log(getLogTag(), "resultCode = " + resultCode);

			if (data == null) {
				LogUtil.log(getLogTag(), "data == null");
			} else {
				LogUtil.log(getLogTag(), data.getStringExtra("start"));
			}
		}
	}
}
