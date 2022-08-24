package com.blog.demo.feature.popup

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import com.blog.demo.LogTool.logi
import com.blog.demo.R

class PopupWindowActivity : Activity(), View.OnClickListener {

    private val LOG_TAG = "PopupWindowActivity"

    private lateinit var mCbTouchable: CheckBox
    private lateinit var mCbFocusable: CheckBox
    private lateinit var mCbOutsideTouchable: CheckBox

    private var mPopupWindow: PopupWindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_feature_popup_popup_window)

        findViewById<Button>(R.id.btn_show_at_location).setOnClickListener(this)

        mCbTouchable = findViewById(R.id.cb_touchable)
        mCbFocusable = findViewById(R.id.cb_focusable)
        mCbOutsideTouchable = findViewById(R.id.cb_outside_touchable)

        findViewById<Button>(R.id.btn_show_drop_down).setOnClickListener(this)
    }

    private fun createPopupWindow(): PopupWindow {
        val popupWindow = PopupWindow(this)
        // 设置宽度
        popupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
        // 设置高度
        popupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
        // 设置背景
        popupWindow.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorProduct)))

        val view: View = layoutInflater.inflate(R.layout.popup_window_picture, null, false)
        view.findViewById<TextView>(R.id.tv_take_photo).setOnClickListener(this)
        view.findViewById<TextView>(R.id.tv_take_picture).setOnClickListener(this)
        view.findViewById<TextView>(R.id.tv_cancel).setOnClickListener(this)

        // 设置界面
        popupWindow.contentView = view
        // true时界面可点
        popupWindow.isTouchable = true
        // true时PopupWindow处理返回键
        popupWindow.isFocusable = true
        // true时点击外部消失，如果touchable为false时，点击界面也消失
        popupWindow.isOutsideTouchable = true

        // dismiss监听器
        popupWindow.setOnDismissListener {
            logi(LOG_TAG, "onDismiss")
            backgroundAlpha(1f)
        }
        return popupWindow
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_show_at_location) {
            mPopupWindow = createPopupWindow()
            // true时界面可点
            mPopupWindow?.isTouchable = mCbTouchable.isChecked
            // true时PopupWindow处理返回键
            mPopupWindow?.isFocusable = mCbFocusable.isChecked
            // true时点击外部消失，如果touchable为false时，点击界面也消失
            mPopupWindow?.isOutsideTouchable = mCbOutsideTouchable.isChecked
            mPopupWindow?.animationStyle = R.style.popup_window_animation_style
            //            backgroundAlpha(0.5f);
            mPopupWindow?.showAtLocation(v, Gravity.BOTTOM, 0, 0)
        } else if (v.id == R.id.btn_show_drop_down) {
            mPopupWindow = createPopupWindow()
            // true时界面可点
            mPopupWindow?.isTouchable = mCbTouchable.isChecked
            // true时PopupWindow处理返回键
            mPopupWindow?.isFocusable = mCbFocusable.isChecked
            // true时点击外部消失，如果touchable为false时，点击界面也消失
            mPopupWindow?.isOutsideTouchable = mCbOutsideTouchable.isChecked
            mPopupWindow?.showAsDropDown(v)
        } else if (v.id == R.id.tv_take_photo) {
            Toast.makeText(this, "take photo", Toast.LENGTH_LONG).show()
        } else if (v.id == R.id.tv_take_picture) {
            Toast.makeText(this, "take picture", Toast.LENGTH_LONG).show()
        } else if (v.id == R.id.tv_cancel) {
            Toast.makeText(this, "cancel", Toast.LENGTH_LONG).show()
            mPopupWindow?.dismiss()
        }
    }

    private fun backgroundAlpha(bgAlpha: Float) {
        val lp = window.attributes
        lp.alpha = bgAlpha //0.0-1.0
        window.attributes = lp
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }

}