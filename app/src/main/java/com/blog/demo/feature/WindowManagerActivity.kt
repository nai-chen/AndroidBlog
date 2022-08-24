package com.blog.demo.feature

import android.app.Activity
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.blog.demo.R

class WindowManagerActivity : Activity() {

    private val REQUEST_OVERLAY_PERMISSION = 1

    private lateinit var mSuspensionView: View
    private lateinit var mWindowManager: WindowManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_feature_window_manager)

        findViewById<Button>(R.id.btn_show_suspension_window).setOnClickListener {
            requestPermission()
        }

        val imageView = ImageView(applicationContext)
        imageView.setImageResource(R.drawable.icon_link)
        mSuspensionView = imageView

        imageView.setOnClickListener {
            Toast.makeText(this@WindowManagerActivity, "Suspension View", Toast.LENGTH_SHORT).show()
            mWindowManager.removeView(mSuspensionView)
        }

        mWindowManager = applicationContext.getSystemService(WINDOW_SERVICE) as WindowManager
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this)) {
                showSuspensionWindow(mSuspensionView)
            } else {
                Toast.makeText(this, "请授权！", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_OVERLAY_PERMISSION) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
                showSuspensionWindow(mSuspensionView)
            }
        }
    }

    private fun showSuspensionWindow(view: View) {
        val lp = WindowManager.LayoutParams()
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL // 窗口位置
        lp.format = PixelFormat.TRANSPARENT // 位图格式
        lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY // 窗口的层级关系
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE // 窗口的模式
        mWindowManager.addView(view, lp)
    }

}