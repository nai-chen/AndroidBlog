package com.blog.demo.control

import android.app.Activity
import android.os.Bundle
import com.blog.demo.control.widget.PhoneTextView

class PhoneTextViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(PhoneTextView(this))
    }

}