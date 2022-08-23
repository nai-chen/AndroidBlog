package com.blog.demo.md

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.blog.demo.R
import com.google.android.material.snackbar.Snackbar

class FloatingActionButtonActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_material_design_floating_action_button)

        findViewById<Button>(R.id.btn_in_coordinator).setOnClickListener(this)
        findViewById<Button>(R.id.btn_in_relative).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val snackbar = Snackbar.make(v!!, "This is Snackbar", Snackbar.LENGTH_LONG)
        snackbar.show()
    }

}