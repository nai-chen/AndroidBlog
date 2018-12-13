package com.blog.demo.lib.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blog.demo.lib.R;

/**
 * Created by cn on 2017/4/5.
 */

public class CustomView extends RelativeLayout {

    private TextView mTvTitleTop, mTvTitleBottom;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = inflate(context, R.layout.layout_custom_view, this);
        mTvTitleTop = (TextView) view.findViewById(R.id.tv_title_top);
        mTvTitleBottom = (TextView) view.findViewById(R.id.tv_title_bottom);
//        mTvTitleTop = new TextView(context);
//        mTvTitleTop.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                getResources().getDimensionPixelSize(R.dimen.font_size_xhdpi_16));
//        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        lp.addRule(ALIGN_PARENT_LEFT);
//        lp.addRule(CENTER_VERTICAL);
//        lp.leftMargin = getResources().getDimensionPixelOffset(R.dimen.margin_xdpi_15);
//        addView(mTvTitleTop, lp);
//
//        mTvTitleBottom = new TextView(context);
//        mTvTitleBottom.setTextSize(TypedValue.COMPLEX_UNIT_PX,
//                getResources().getDimensionPixelSize(R.dimen.font_size_xhdpi_12));
//        lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//        lp.addRule(ALIGN_PARENT_RIGHT);
//        lp.addRule(CENTER_VERTICAL);
//        lp.rightMargin = getResources().getDimensionPixelOffset(R.dimen.margin_xdpi_15);
//        addView(mTvTitleBottom, lp);
    }

    public void setTitleTop(CharSequence title) {
        mTvTitleTop.setText(title);
    }

    public void setTitleBottom(CharSequence title) {
        mTvTitleBottom.setText(title);
    }

}
