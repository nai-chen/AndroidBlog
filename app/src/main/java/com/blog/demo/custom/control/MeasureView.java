package com.blog.demo.custom.control;

import com.blog.demo.LogUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class MeasureView extends View {
	private final static String LOGTAG = "CMeasureView";

	public MeasureView(Context context) {
		super(context);
	}

	public MeasureView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int mode = MeasureSpec.getMode(widthMeasureSpec);
		int size = MeasureSpec.getSize(widthMeasureSpec);

		LogUtil.log(LOGTAG, "width = " + size + " mode = " + getMode(mode));

		mode = MeasureSpec.getMode(heightMeasureSpec);
		size = MeasureSpec.getSize(heightMeasureSpec);

		LogUtil.log(LOGTAG, "height = " + size + " mode = " + getMode(mode));
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

}