package com.blog.demo.custom.control;

import com.blog.demo.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class Param2TextView extends TextView {

	public Param2TextView(Context context) {
		this(context, null);
	}

	public Param2TextView(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Param2TextView);
		int color = a.getColor(R.styleable.Param2TextView_bg, 0);
		a.recycle();

//		setBackgroundColor(color);
		setTextColor(color);
	}

}
