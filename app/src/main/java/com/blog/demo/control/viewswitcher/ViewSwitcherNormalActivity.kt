package com.blog.demo.control.viewswitcher

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ViewSwitcher
import com.blog.demo.R

class ViewSwitcherNormalActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_view_switcher_normal)

        var viewSwitcher: ViewSwitcher = findViewById(R.id.view_switcher)
        findViewById<Button>(R.id.btn_show_next).setOnClickListener {
            viewSwitcher.showNext()
        }
    }

}