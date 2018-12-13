package com.blog.demo.custom.control;

import com.blog.demo.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class Param1TextView extends TextView {

	public Param1TextView(Context context) {
		this(context, null);
	}

	public Param1TextView(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Param1TextView);
		int color = a.getColor(R.styleable.Param1TextView_bg, 0);
		a.recycle();

		setBackgroundColor(color);
	}

}
