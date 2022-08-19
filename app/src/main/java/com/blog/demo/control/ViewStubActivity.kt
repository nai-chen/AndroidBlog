package com.blog.demo.control

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewStub
import android.widget.Button
import android.widget.TextView
import com.blog.demo.R

class ViewStubActivity : Activity(), View.OnClickListener {
    private lateinit var viewStub: ViewStub
    private var tvInflate: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_view_stub)

        findViewById<Button>(R.id.btn_inflate).setOnClickListener(this)
        findViewById<Button>(R.id.btn_set_visibility).setOnClickListener(this)
        viewStub = findViewById(R.id.view_stub)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_inflate) {
            if (tvInflate == null) {
                tvInflate = viewStub.inflate() as TextView
            }
        } else if (v.id == R.id.btn_set_visibility) {
            if (viewStub.visibility != View.VISIBLE) {
                viewStub.visibility = View.VISIBLE
            }
        }
    }

}