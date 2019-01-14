package com.blog.demo.component.activity;

import com.blog.demo.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LifeComplexActivity extends AbstractLifeActivity {
	private final static String LOG_TAG = "LifeComplexActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_component_life_complex);
		
		findViewById(R.id.btn_start_new_activity).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LifeComplexActivity.this, LifeComplexOtherActivity.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected String getLogTag() {
		return LOG_TAG;
	}

}
