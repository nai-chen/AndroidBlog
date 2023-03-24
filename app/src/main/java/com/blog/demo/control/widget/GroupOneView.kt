package com.blog.demo.control.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.blog.demo.R

class GroupOneView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        LinearLayout(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        inflate(context, R.layout.view_group_one, this)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = "This is group one."
    }

}