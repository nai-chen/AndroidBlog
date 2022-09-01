package com.blog.demo.third

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.blog.demo.R
import com.umeng.analytics.MobclickAgent

class UmengActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_third_umeng)
        findViewById<View>(R.id.btn_click).setOnClickListener {
            MobclickAgent.onEvent(this@UmengActivity, "btn_click")
        }
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }

}