package com.blog.demo.md.collapsingtoolbar

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.blog.demo.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

class CollapsingToolbarLayoutNormalActivity : Activity(), View.OnClickListener {

    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_collapsing_toolbar_layout_normal)
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout)

        findViewById<Button>(R.id.btn_scroll).setOnClickListener(this)
        findViewById<Button>(R.id.btn_enter_always).setOnClickListener(this)
        findViewById<Button>(R.id.btn_enter_always_collapsed).setOnClickListener(this)
        findViewById<Button>(R.id.btn_exit_until_collapsed).setOnClickListener(this)
        findViewById<Button>(R.id.btn_snap).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val lp = collapsingToolbarLayout.layoutParams as AppBarLayout.LayoutParams
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
        collapsingToolbarLayout.layoutParams = lp
    }

}