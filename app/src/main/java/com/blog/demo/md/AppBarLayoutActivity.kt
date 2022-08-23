package com.blog.demo.md

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.blog.demo.R
import com.google.android.material.appbar.AppBarLayout

class AppBarLayoutActivity : Activity(), View.OnClickListener {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_app_bar_layout)
        toolbar = findViewById(R.id.toolbar)

        findViewById<Button>(R.id.btn_scroll).setOnClickListener(this)
        findViewById<Button>(R.id.btn_enter_always).setOnClickListener(this)
        findViewById<Button>(R.id.btn_enter_always_collapsed).setOnClickListener(this)
        findViewById<Button>(R.id.btn_exit_until_collapsed).setOnClickListener(this)
        findViewById<Button>(R.id.btn_snap).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val lp = toolbar.layoutParams as AppBarLayout.LayoutParams
        if (v.id == R.id.btn_scroll) {
            lp.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
        } else if (v.id == R.id.btn_enter_always) {
            lp.scrollFlags = (AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS)
        } else if (v.id == R.id.btn_enter_always_collapsed) {
            lp.scrollFlags = (AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                    or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED)
        } else if (v.id == R.id.btn_exit_until_collapsed) {
            lp.scrollFlags = (AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED)
        } else if (v.id == R.id.btn_snap) {
            lp.scrollFlags = (AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                    or AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP)
        }
        toolbar.layoutParams = lp
    }

}