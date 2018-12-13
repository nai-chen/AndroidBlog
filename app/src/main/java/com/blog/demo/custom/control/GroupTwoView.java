package com.blog.demo.custom.control;

import com.blog.demo.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GroupTwoView extends LinearLayout {

	public GroupTwoView(Context context) {
		super(context);
	}

	public GroupTwoView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		TextView tv = (TextView) findViewById(R.id.tv);
		tv.setText("change by manual two");
	}

}
