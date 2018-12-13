package com.blog.demo.custom.control;

import com.blog.demo.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Param3TextView extends TextView {

	public Param3TextView(Context context) {
		this(context, null);
	}

	public Param3TextView(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Param3TextView);
		int orientation = a.getInt(R.styleable.Param3TextView_android_orientation, -1);
		a.recycle();

		if (orientation == LinearLayout.HORIZONTAL) {
			setText("horizontal");
		} else if (orientation == LinearLayout.VERTICAL) {
			setText("vertical");
		}
	}

}
