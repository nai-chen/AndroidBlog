package com.blog.demo.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blog.demo.R;

public class AttrAssignTextView extends androidx.appcompat.widget.AppCompatTextView {

    public AttrAssignTextView(Context context) {
        this(context, null);
    }

    public AttrAssignTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttrAssignTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AttrAssignTextView);
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AttrAssignTextView,
//                R.attr.attrAssignStyle, 0);
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AttrAssignTextView,
//                0, R.style.attrAssignDefStyleRes);
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AttrAssignTextView,
//                R.attr.attrAssignStyle, R.style.attrAssignDefStyleRes);
        String attr1 = a.getString(R.styleable.AttrAssignTextView_attr1);
        String attr2 = a.getString(R.styleable.AttrAssignTextView_attr2);
        String attr3 = a.getString(R.styleable.AttrAssignTextView_attr3);
        String attr4 = a.getString(R.styleable.AttrAssignTextView_attr4);
        String attr5 = a.getString(R.styleable.AttrAssignTextView_attr5);

        a.recycle();

        setText((attr1 != null ? attr1 + "\n" : "")
                + (attr2 != null ? attr2 + "\n" : "")
                + (attr3 != null ? attr3 + "\n" : "")
                + (attr4 != null ? attr4 + "\n" : "")
                + (attr5 != null ? attr5 + "\n" : ""));
    }

}
