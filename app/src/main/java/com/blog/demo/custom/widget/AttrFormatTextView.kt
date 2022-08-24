package com.blog.demo.custom.widget

import android.content.Context
import android.util.AttributeSet
import com.blog.demo.R

class AttrFormatTextView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AttrFormatTextView)

        val refValue = a.getResourceId(R.styleable.AttrFormatTextView_attrReference, 0)
        val colorValue = a.getColor(R.styleable.AttrFormatTextView_attrColor, 0)
        val boolValue = a.getBoolean(R.styleable.AttrFormatTextView_attrBoolean, false)
        val dimenValue = a.getDimensionPixelSize(R.styleable.AttrFormatTextView_attrDimension, 0)
        val floatValue = a.getFloat(R.styleable.AttrFormatTextView_attrFloat, 0f)
        val intValue = a.getInteger(R.styleable.AttrFormatTextView_attrInteger, 0)
        val strValue = a.getString(R.styleable.AttrFormatTextView_attrString)
        val fractionValue = a.getFraction(R.styleable.AttrFormatTextView_attrFraction, 100, 200, 0f)
        val enumValue = a.getInt(R.styleable.AttrFormatTextView_attrEnum, 0)
        val flagValue = a.getInt(R.styleable.AttrFormatTextView_attrFlag, 0)

        a.recycle()

        if (colorValue != 0) {
            setTextColor(colorValue)
        }

        val text = (if (refValue != 0) "ref = ${resources.getString(refValue)}\n" else "") +
                (if (boolValue) "bool = true\n" else "") +
                (if (dimenValue != 0) "dimen = $dimenValue\n" else "") +
                (if (floatValue != 0f) "float = $floatValue\n" else "") +
                (if (intValue != 0) "integer = $intValue\n" else "") +
                (if (strValue != null) "string = $strValue\n" else "") +
                (if (fractionValue != 0f) "fraction = $fractionValue\n" else "") +
                (if (enumValue != 0) "enum = $enumValue\n" else "") +
                (if (flagValue != 0) "flag = ${getFlagValue(flagValue)}" else "")

        setText(text)
    }

    private fun getFlagValue(flag: Int): String {
        var flagValue = ""
        if (flag and 0x01 != 0) {
            flagValue += "Top"
        }
        if (flag and 0x02 != 0) {
            flagValue += if (flagValue.isEmpty()) "" else " | "
            flagValue += "Bottom"
        }
        if (flag and 0x10 != 0) {
            flagValue += if (flagValue.isEmpty()) "" else " | "
            flagValue += "Left"
        }
        if (flag and 0x20 != 0) {
            flagValue += if (flagValue.isEmpty()) "" else " | "
            flagValue += "Right"
        }
        return flagValue
    }

}