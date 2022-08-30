package com.blog.demo.application

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import com.blog.demo.LogTool.logi
import com.blog.demo.R

class SoftInputManagerActivity : Activity() {

    private val LOG_TAG = "SoftInputManagerActivity"

    private var mTranslate = false
    private lateinit var mBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_application_soft_input_manager)

        mBtn = findViewById(R.id.btn)

        window.decorView.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
                logi(LOG_TAG, "onLayoutChange")
                onLayout()
            }

        window.decorView.viewTreeObserver.addOnGlobalLayoutListener {
                logi(LOG_TAG, "onGlobalLayout")
                onLayout()
            }

        var editText: EditText = findViewById(R.id.et)
        findViewById<Button>(R.id.btn_show_soft).setOnClickListener {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            editText.requestFocus()
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }

        findViewById<Button>(R.id.btn_hide_soft).setOnClickListener {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        findViewById<EditText>(R.id.et1).requestFocus()
    }

    private fun onLayout() {
        //获取当前屏幕内容的高度
        val screenHeight = window.decorView.height

        //获取View可见区域的bottom
        val rect = Rect()
        window.decorView.getWindowVisibleDisplayFrame(rect)
        logi(LOG_TAG, "screenHeight = $screenHeight")
        logi(LOG_TAG, "rect.top = " + rect.top)
        logi(LOG_TAG, "rect.bottom = " + rect.bottom)

        if (screenHeight - getNavigatorBarHeight() > rect.bottom) {
            // 获取按钮的左上角，按钮高度为40dp
            val location = IntArray(2)
            mBtn.getLocationOnScreen(location)
            val bottom = location[1] + resources.getDimensionPixelSize(R.dimen.margin_dpi_50)

            logi(LOG_TAG, "location[1] = ${location[1]}, bottom = $bottom")

            // 如果按钮被覆盖，移动整个界面向上移动
            if (bottom > rect.bottom) {
                window.decorView.scrollBy(0, bottom - rect.bottom)
                mTranslate = true
            }
        } else {
            if (mTranslate) {
                window.decorView.scrollTo(0, 0)
                mTranslate = false
            }
        }

    }

    private fun getNavigatorBarHeight(): Int {
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        val height = resources.getDimensionPixelSize(resourceId)
        logi(LOG_TAG, "Status Bar Height = $height")
        return height
    }

}