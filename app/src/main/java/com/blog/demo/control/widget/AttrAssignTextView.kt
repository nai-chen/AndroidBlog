package com.blog.demo.control.widget

import android.content.Context
import android.util.AttributeSet
import com.blog.demo.R

class AttrAssignTextView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AttrAssignTextView)
//        val a = context.obtainStyledAttributes(attrs, R.styleable.AttrAssignTextView,
//            R.attr.attrAssignStyle, 0)
//        val a = context.obtainStyledAttributes(attrs, R.styleable.AttrAssignTextView,
//            0, R.style.attrAssignDefStyleRes)
//        val a = context.obtainStyledAttributes(attrs, R.styleable.AttrAssignTextView,
//            R.attr.attrAssignStyle, R.style.attrAssignDefStyleRes);
        val attr1 = a.getString(R.styleable.AttrAssignTextView_attr1)
        val attr2 = a.getString(R.styleable.AttrAssignTextView_attr2)
        val attr3 = a.getString(R.styleable.AttrAssignTextView_attr3)
        val attr4 = a.getString(R.styleable.AttrAssignTextView_attr4)
        val attr5 = a.getString(R.styleable.AttrAssignTextView_attr5)

        a.recycle()

        text = (if (attr1 != null) "$attr1\n" else "") +
                (if (attr2 != null) "$attr2\n" else "") +
                (if (attr3 != null) "$attr3\n" else "") +
                (if (attr4 != null) "$attr4\n" else "") +
                (if (attr5 != null) "$attr5\n" else "")
    }


}