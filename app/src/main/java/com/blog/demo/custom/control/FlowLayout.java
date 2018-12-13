package com.blog.demo.custom.control;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

/**
 * Created by cn on 2017/12/21.
 */

public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = 0;

        int count = getChildCount();
        int startX = 0;
        int childHeight = 0;

        for (int index = 0; index < count; index++) {
            View child = getChildAt(index);

            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            if (startX + child.getMeasuredWidth() > width) {
                height += childHeight;

                childHeight = child.getMeasuredHeight();
                startX = child.getMeasuredWidth();
            } else {
                if (childHeight < child.getMeasuredHeight()) {
                    childHeight = child.getMeasuredHeight();
                }
                startX += child.getMeasuredWidth();
            }
        }
        height += childHeight;

        LogUtil.log("FlowLayout", "width = " + width);
        LogUtil.log("FlowLayout", "height = " + height);

        setMeasuredDimension(resolveSize(width, widthMeasureSpec),
                resolveSize(height, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();

        int startX = 0;
        int height = 0;
        int width = r - l;
        int childHeight = 0;
        int startIndex = 0;

        for (int index = 0; index < count; index++) {
            View child = getChildAt(index);

            if (startX + child.getMeasuredWidth() > width) {
                if (index > startIndex) {
                    layoutChild(height, childHeight, startIndex, index);
                }

                startIndex = index;
                height += childHeight;
                startX = 0;
                childHeight = child.getMeasuredHeight();
            } else {
                if (childHeight < child.getMeasuredHeight()) {
                    childHeight = child.getMeasuredHeight();
                }
            }
            startX += child.getMeasuredWidth();
        }

        if (startIndex < count) {
            layoutChild(height, childHeight, startIndex, count);
        }
    }

    private void layoutChild(int top, int height, int startIndex, int endIndex) {
        int startX = 0;
        for (int index = startIndex; index < endIndex; index++) {
            View child = getChildAt(index);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();

            int offset = 0;
            if (lp.gravity == LayoutParams.GRAVITY_CENTER) {
                offset = (height - child.getMeasuredHeight()) / 2;
            } else if (lp.gravity == LayoutParams.GRAVITY_BOTTOM) {
                offset = height - child.getMeasuredHeight();
            }
            child.layout(startX, top + offset,
                    startX + child.getMeasuredWidth(),
                    top + offset + child.getMeasuredHeight());
            startX += child.getMeasuredWidth();
        }
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p.width, p.height);
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        public final static int GRAVITY_TOP         = 0;
        public final static int GRAVITY_CENTER      = 1;
        public final static int GRAVITY_BOTTOM      = 2;
        int gravity;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.FlowLayout_Layout);

            gravity = a.getInt(R.styleable.FlowLayout_Layout_gravity, GRAVITY_TOP);

            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

}
