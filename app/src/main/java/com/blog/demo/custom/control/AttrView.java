package com.blog.demo.custom.control;

import com.blog.demo.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

public class AttrView extends TextView {

	public AttrView(Context context) {
		this(context, null);
	}

	public AttrView(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AttrTextView);

		int resId = a.getResourceId(R.styleable.AttrTextView_attrReference, 0);
		int color = a.getColor(R.styleable.AttrTextView_attrColor, 0);
		boolean bool = a.getBoolean(R.styleable.AttrTextView_attrBoolean, false);
		int dimen = a.getDimensionPixelSize(R.styleable.AttrTextView_attrDimension, 0);
		float f = a.getFloat(R.styleable.AttrTextView_attrFloat, 0);
		int i = a.getInteger(R.styleable.AttrTextView_attrInteger, 0);
		float fraction = a.getFraction(R.styleable.AttrTextView_attrFraction, 100, 200, 0);
		int e = a.getInt(R.styleable.AttrTextView_attrEnum, 0);
		int di = a.getDimensionPixelSize(R.styleable.AttrTextView_attrMultiDimen, 0);

		CharSequence multi = a.getText(R.styleable.AttrTextView_attrMulti);

		a.recycle();

		String text = (resId != 0 ? "reference = " + getResources().getString(resId) + "\n" : "") +
				(color != 0 ?  "color:" + color + "\n" : "") +
				(bool ? "bool = true\n" : "") +
				(dimen != 0 ? "dimen = " + dimen + "\n" : "") +
				(f != 0 ? "float = " + f + "\n" : "") +
				(i != 0 ? "integer = " + i + "\n" : "") +
				(fraction != 0 ? "fraction = " + fraction + "\n" : "") +
				(e != 0 ? "enum = " + e + "\n" : "") +
				(multi != null ? "multi = " + multi + "\n" : "") +
				(di != 0 ? "multiDimen = " + di + "\n" : "");
		setText(text);
	}

}
