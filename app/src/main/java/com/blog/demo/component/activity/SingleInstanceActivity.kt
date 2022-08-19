package com.blog.demo.component.activity

class SingleInstanceActivity : StandardActivity() {

    private val LOG_TAG = "SingleInstanceActivity"

    override fun getLogTag(): String {
        return LOG_TAG
    }
}