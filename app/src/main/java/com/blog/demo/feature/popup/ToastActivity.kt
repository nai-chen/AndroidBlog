package com.blog.demo.feature.popup

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.blog.demo.R

class ToastActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_feature_popup_toast)

        findViewById<Button>(R.id.btn_show_toast).setOnClickListener(this)
        findViewById<Button>(R.id.btn_show_center_toast).setOnClickListener(this)
        findViewById<Button>(R.id.btn_show_custom_toast).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_show_toast) {
            Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show()
        } else if (v.id == R.id.btn_show_center_toast) {
            val toast = Toast.makeText(this, "自定义位置Toast", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        } else if (v.id == R.id.btn_show_custom_toast) {
            val toast = Toast(this)
            toast.view = layoutInflater.inflate(R.layout.toast_custom_view, null)
            toast.duration = Toast.LENGTH_LONG
            toast.show()
        }
    }

}