package com.blog.demo.custom.control;

import com.blog.demo.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GroupOneView extends LinearLayout {

	public GroupOneView(Context context) {
		this(context, null);
	}

	public GroupOneView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		View.inflate(context, R.layout.view_group_one, this);

		TextView tv = (TextView) findViewById(R.id.tv);
		tv.setText("change by manual");
	}

}
