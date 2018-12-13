package com.blog.demo.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.blog.demo.R;
import com.blog.demo.custom.control.VelocityTrackerView;

public class VelocityTrackerActivity extends Activity implements View.OnClickListener {
	private VelocityTrackerView mView;
	private EditText mEtXDistance, mEtYDistance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_velocity_tracker);

		findViewById(R.id.btn_scroll_to).setOnClickListener(this);
		findViewById(R.id.btn_scroll_by).setOnClickListener(this);
		findViewById(R.id.btn_start_scroll).setOnClickListener(this);

		mView = (VelocityTrackerView) findViewById(R.id.view);
		mEtXDistance = (EditText) findViewById(R.id.et_x_distance);
		mEtYDistance = (EditText) findViewById(R.id.et_y_distance);
	}

	@Override
	public void onClick(View v) {
		int x = parseInt(mEtXDistance);
		int y = parseInt(mEtYDistance);
		switch (v.getId()) {
			case R.id.btn_scroll_to:
				mView.scrollTo(x, y);
				break;
			case R.id.btn_scroll_by:
				mView.scrollBy(x, y);
				break;
			case R.id.btn_start_scroll:
				mView.backToOrigin(mView.getScrollX(), mView.getScrollY());
		}
	}

	private int parseInt(EditText et) {
		try {
			return Integer.parseInt(et.getText().toString());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
