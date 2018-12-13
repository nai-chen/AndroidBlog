package com.blog.demo.custom.control;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

/**
 * Created by cn on 2017/10/30.
 */

public class ParamAssignView extends View {

    public ParamAssignView(Context context) {
        this(context, null);
    }

    public ParamAssignView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.attrAssignStyle);
//        super(context, attrs);
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ParamAssignView);
//
//        String attr1 = a.getString(R.styleable.ParamAssignView_attr1);
//        String attr2 = a.getString(R.styleable.ParamAssignView_attr2);
//        String attr3 = a.getString(R.styleable.ParamAssignView_attr3);
//        String attr4 = a.getString(R.styleable.ParamAssignView_attr4);
//
//        LogUtil.log("ParamAssignView", "attr1 = " + attr1);
//        LogUtil.log("ParamAssignView", "attr2 = " + attr2);
//        LogUtil.log("ParamAssignView", "attr3 = " + attr3);
//        LogUtil.log("ParamAssignView", "attr4 = " + attr4);
//
//        a.recycle();
    }

    public ParamAssignView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ParamAssignView,
//                defStyleAttr, R.style.DefaultParamAssignStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ParamAssignView,
                0, R.style.DefaultParamAssignStyle);

        String attr1 = a.getString(R.styleable.ParamAssignView_attr1);
        String attr2 = a.getString(R.styleable.ParamAssignView_attr2);
        String attr3 = a.getString(R.styleable.ParamAssignView_attr3);
        String attr4 = a.getString(R.styleable.ParamAssignView_attr4);
        String attr5 = a.getString(R.styleable.ParamAssignView_attr5);

        LogUtil.log("ParamAssignView", "attr1 = " + attr1);
        LogUtil.log("ParamAssignView", "attr2 = " + attr2);
        LogUtil.log("ParamAssignView", "attr3 = " + attr3);
        LogUtil.log("ParamAssignView", "attr4 = " + attr4);
        LogUtil.log("ParamAssignView", "attr5 = " + attr5);

        a.recycle();
    }

}
