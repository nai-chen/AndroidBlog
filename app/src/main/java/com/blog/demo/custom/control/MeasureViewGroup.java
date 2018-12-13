package com.blog.demo.custom.control;

import com.blog.demo.LogUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MeasureViewGroup extends ViewGroup {
	private final static String LOGTAG = "CMeasureViewGroup";

	public MeasureViewGroup(Context context) {
		super(context);
	}

	public MeasureViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = getMeasureSize(widthMeasureSpec);
		int height = getMeasureSize(heightMeasureSpec);

		setMeasuredDimension(width, height);

		measureChildren(widthMeasureSpec, heightMeasureSpec);
	}

	private int getMeasureSize(int measureSpec) {
		int mode = MeasureSpec.getMode(measureSpec);
		int size = MeasureSpec.getSize(measureSpec);

		LogUtil.log(LOGTAG, "size = " + size + " mode = " + getMode(mode));

		if (mode == MeasureSpec.UNSPECIFIED) {
			return 0;
		} else if (mode == MeasureSpec.AT_MOST ||
				mode == MeasureSpec.EXACTLY) {
			return size;
		} else {
			return 0;
		}
	}

	private String getMode(int mode) {
		if (mode == MeasureSpec.UNSPECIFIED) {
			return "UNSPECIFIED";
		} else if (mode == MeasureSpec.AT_MOST) {
			return "AT_MOST";
		} else if (mode == MeasureSpec.EXACTLY) {
			return "EXACTLY";
		} else {
			return "unknow mode";
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		LogUtil.log(LOGTAG, "left = "+l + " top = "+t + " right = "+r + " bottom = "+b);
		int totalHeight = t;
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);

			int measureWidth = child.getMeasuredWidth();
			int measureHeight = child.getMeasuredHeight();

			child.layout(l, totalHeight, l + measureWidth, totalHeight + measureHeight);

			totalHeight += measureHeight;
		}
	}

}