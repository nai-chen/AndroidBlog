package com.blog.demo.custom.control;

import com.blog.demo.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GroupParamView extends LinearLayout {

	public GroupParamView(Context context) {
		this(context, null);
	}

	public GroupParamView(Context context, AttributeSet attrs) {
		super(context, attrs);

		View.inflate(context, R.layout.view_group_one, this);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GroupParamView);
		String text = a.getString(R.styleable.GroupParamView_text);
		int textSize = a.getInt(R.styleable.GroupParamView_textSize, 0);
		a.recycle();

		TextView tv = (TextView) findViewById(R.id.tv);
		if (text != null) {
			tv.setText(text);
		}

		if (textSize != 0) {
			tv.setTextSize(textSize);
		}
	}

}