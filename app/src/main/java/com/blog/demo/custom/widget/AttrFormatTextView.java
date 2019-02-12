package com.blog.demo.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.blog.demo.R;

public class AttrFormatTextView extends TextView {
    public AttrFormatTextView(Context context) {
        this(context, null);
    }

    public AttrFormatTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttrFormatTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AttrFormatTextView);
        int refValue = a.getResourceId(R.styleable.AttrFormatTextView_attrReference, 0);
        int colorValue = a.getColor(R.styleable.AttrFormatTextView_attrColor, 0);
        boolean boolValue = a.getBoolean(R.styleable.AttrFormatTextView_attrBoolean, false);
        int dimenValue = a.getDimensionPixelSize(R.styleable.AttrFormatTextView_attrDimension, 0);
        float floatValue = a.getFloat(R.styleable.AttrFormatTextView_attrFloat, 0);
        int intValue = a.getInteger(R.styleable.AttrFormatTextView_attrInteger, 0);
        String strValue = a.getString(R.styleable.AttrFormatTextView_attrString);
        float fractionValue = a.getFraction(R.styleable.AttrFormatTextView_attrFraction, 100, 200, 0);
        int enumValue = a.getInt(R.styleable.AttrFormatTextView_attrEnum, 0);
        int flagValue = a.getInt(R.styleable.AttrFormatTextView_attrFlag, 0);
        a.recycle();

        if (colorValue != 0) {
            setTextColor(colorValue);
        }

        String text = (refValue != 0 ? "ref = " + getResources().getString(refValue) + "\n" : "")
                + (boolValue ? "bool = true\n" : "")
                + (dimenValue != 0 ? "dimen = " + dimenValue + "\n" : "")
                + (floatValue != 0 ? "float = " + floatValue + "\n" : "")
                + (intValue != 0 ? "integer = " + intValue + "\n" : "")
                + (strValue != null ? "string = " + strValue + "\n" : "")
                + (fractionValue != 0 ? "fraction = " + fractionValue + "\n" : "")
                + (enumValue != 0 ? "enum = " + enumValue + "\n" : "")
                + (flagValue != 0 ? "flag = " + getFlagValue(flagValue) + "\n" : "");

        setText(text);
    }

    private String getFlagValue(int flag) {
        String flagValue = "";
        if ((flag & 0x01) != 0) {
            flagValue += "Top";
        }
        if ((flag & 0x02) != 0) {
            flagValue += flagValue.length() == 0 ? "" : " | ";
            flagValue += "Bottom";
        }
        if ((flag & 0x10) != 0) {
            flagValue += flagValue.length() == 0 ? "" : " | ";
            flagValue += "Left";
        }
        if ((flag & 0x20) != 0) {
            flagValue += flagValue.length() == 0 ? "" : " | ";
            flagValue += "Right";
        }
        return flagValue;
    }

}
