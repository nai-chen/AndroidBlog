package com.blog.demo.component.activity

import android.os.Bundle
import android.widget.Button
import com.blog.demo.LogTool
import com.blog.demo.R

class LifeActivity : AbstractLifeActivity() {

    private val LOG_TAG = "LifeActivity"

    private var iValue = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LogTool.logi(LOG_TAG, "i = $iValue")

        setContentView(R.layout.activity_component_life)
        findViewById<Button>(R.id.btn_change_value).setOnClickListener {
            iValue += 10
            LogTool.logi(LOG_TAG, "i = $iValue")
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        LogTool.logi(LOG_TAG, "onSaveInstanceState")

        outState.putInt("key1", 23)
        outState.putInt("key2", iValue)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        LogTool.logi(LOG_TAG, "onRestoreInstanceState")
        LogTool.logi(LOG_TAG, "key1 = " + savedInstanceState.getInt("key1"))
        LogTool.logi(LOG_TAG, "key2 = " + savedInstanceState.getInt("key2"))
    }

    override fun getLogTag(): String {
        return LOG_TAG
    }

}