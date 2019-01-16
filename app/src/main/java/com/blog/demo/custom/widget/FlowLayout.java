package com.blog.demo.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.blog.demo.R;

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
            if (child.getVisibility() != View.GONE) {
                // 计算child宽高
                measureChild(child, widthMeasureSpec, heightMeasureSpec);

                // 如果超过一行，换行重新开始
                if (startX + child.getMeasuredWidth() > width) {
                    height += childHeight;

                    childHeight = child.getMeasuredHeight();
                    startX = child.getMeasuredWidth();
                } else {
                    childHeight = Math.max(childHeight, child.getMeasuredHeight());
                    startX += child.getMeasuredWidth();
                }
            }
        }
        height += childHeight;

        setMeasuredDimension(widthMeasureSpec,
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();

        int startX = 0;
        int height = 0;
        int width = right - left;
        int childHeight = 0;
        int startIndex = 0;

        for (int index = 0; index < count; index++) {
            View child = getChildAt(index);
            if (child.getVisibility() != View.GONE) {

                // 如果超过一行，换行显示
                if (startX + child.getMeasuredWidth() > width) {
                    if (index > startIndex) {
                        layoutChildren(height, childHeight, startIndex, index);
                    }

                    startIndex = index;
                    height += childHeight;
                    startX = child.getMeasuredWidth();
                    childHeight = child.getMeasuredHeight();
                } else {
                    childHeight = Math.max(childHeight, child.getMeasuredHeight());
                    startX += child.getMeasuredWidth();
                }
            }
        }

        if (startIndex < count) {
            layoutChildren(height, childHeight, startIndex, count);
        }
    }

    private void layoutChildren(int top, int childHeight, int startIndex, int endIndex) {
        int startX = 0;

        for (int index = startIndex; index < endIndex; index++) {
            View child = getChildAt(index);
            if (child.getVisibility() != View.GONE) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();

                int offsetY = 0;
                if (lp.gravity == LayoutParams.GRAVITY_MIDDLE) {
                    offsetY = (childHeight - child.getMeasuredHeight()) / 2;
                } else if (lp.gravity == LayoutParams.GRAVITY_BOTTOM) {
                    offsetY = childHeight - child.getMeasuredHeight();
                }

                child.layout(startX, top + offsetY,
                        startX + child.getMeasuredWidth(),
                        top + offsetY + child.getMeasuredHeight());

                startX += child.getMeasuredWidth();
            }
        }
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p != null && p instanceof LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        public static final int GRAVITY_TOP         = 0;
        public static final int GRAVITY_MIDDLE      = 1;
        public static final int GRAVITY_BOTTOM      = 2;

        int gravity = GRAVITY_TOP;

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

            if (source instanceof LayoutParams) {
                gravity = ((LayoutParams) source).gravity;
            }
        }

    }

}
