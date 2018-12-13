package com.blog.demo.custom.control;

/**
 * Created by cn on 2017/10/20.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blog.demo.R;

public class ViewPagerTitle extends LinearLayout {

    private TextView[] mTvTitles;
    private int mTitleCount;
    private int mTitleWidth;

    private int mTranslationX;
    private int mTitleBarHeight;
    private Paint mPaint = new Paint();

    private ViewPager mViewPager;

    private OnPageChangeListener mPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            setSelect(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            scroll(position, positionOffset);
        }
    };

    public ViewPagerTitle(Context context) {
        this(context, null);
    }

    public ViewPagerTitle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerTitle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setOrientation(HORIZONTAL);
        mPaint.setColor(context.getResources().getColor(R.color.color_3179ff));
        mTitleBarHeight = context.getResources().getDimensionPixelSize(R.dimen.margin_xdpi_2);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (mTitleCount == 0) {
            mTitleWidth = w;
        } else {
            mTitleWidth = w / mTitleCount;
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        canvas.save();
        canvas.translate(mTranslationX, getHeight() - mTitleBarHeight);
        canvas.drawRect(getTitleStartX(), 0, getTitleEndX(), mTitleBarHeight, mPaint);
        canvas.restore();
    }

    protected int getTitleStartX() {
        return mTitleWidth / 6;
    }

    protected int getTitleEndX() {
        return mTitleWidth * 5 / 6;
    }

    public void setTitle(String[] titles) {
        removeAllViews();

        mTitleCount = titles.length;
        mTvTitles = new TextView[mTitleCount];
        setWeightSum(mTitleCount);

        for (int index = 0; index < mTitleCount; index++) {
            TextView textView = initTitle(index);
            textView.setText(titles[index]);

            mTvTitles[index] = textView;
        }
        setSelect(0);
        postInvalidate();
    }

    protected TextView initTitle(final int position) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        TextView textView = (TextView) inflater.inflate(R.layout.view_page_title, this, false);
        addView(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelect(position);
                mViewPager.setCurrentItem(position, true);
            }
        });

        return textView;
    }

    public void setSelect(int position) {
        for (int index = 0; index < mTitleCount; index++) {
            mTvTitles[index].setSelected(index == position);
        }
        mTranslationX = position * mTitleWidth;
    }

    public void scroll(int position, float offset) {
        mTranslationX = position * mTitleWidth + (int) (mTitleWidth * offset);
        postInvalidate();
    }

    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(mPageChangeListener);
    }

}
