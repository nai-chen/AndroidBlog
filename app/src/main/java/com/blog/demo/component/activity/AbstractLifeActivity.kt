package com.blog.demo.component.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.blog.demo.LogTool

abstract class AbstractLifeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogTool.logi(getLogTag(), "onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        LogTool.logi(getLogTag(), "onRestart")
    }

    override fun onStart() {
        super.onStart()
        LogTool.logi(getLogTag(), "onStart")
    }

    override fun onResume() {
        super.onResume()
        LogTool.logi(getLogTag(), "onResume")
    }

    override fun onPause() {
        super.onPause()
        LogTool.logi(getLogTag(), "onPause")
    }

    override fun onStop() {
        super.onStop()
        LogTool.logi(getLogTag(), "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogTool.logi(getLogTag(), "onDestroy")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        LogTool.logi(getLogTag(), "onNewIntent")
    }

    open fun getLogTag(): String {
        return "AbstractLifeActivity"
    }

}