package com.blog.demo.custom.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.blog.demo.R

class GroupTwoView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    LinearLayout(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    override fun onFinishInflate() {
        super.onFinishInflate()
        val tv: TextView = findViewById(R.id.text_view)
        tv.text = "This is group two."
    }

}